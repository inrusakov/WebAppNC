package com.example.controller;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRequestForm;
import com.example.model.Traveling.JourneyRole;
import com.example.model.User;
import com.example.repos.TravelRepository;
import com.example.service.AuthenticationService;
import com.example.service.traveling.JourneyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// TODO: [TRAVELING] Добавить выпадающее меню поиска с возможностью выбора параметров запроса к БД.
// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ) (визуальное изменение стиля оформления)
// TODO: [TRAVELING] Убрать возможность находить в поиске путешествия, недоступные пользователю.
@Controller
public class TravellingController {

    @Autowired
    private JourneyServiceImpl journeyService;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private JourneyRequestForm journeyRequestForm;

    @GetMapping("/travel/journey/list")
    String journey_list(
            Model model,
            JourneyRequestForm journeyRequestForm,
            @RequestParam(name = "req", defaultValue = "", required = false) String req
    ) {
        String response = "Travelling/journey_list.html";
        journeyRequestForm.setApplicant(AuthenticationService.getCurrentUser());
        this.journeyRequestForm = journeyRequestForm;

        switch (req){
            case "opn":
                model.addAttribute("journey_list", travelRepository.findByIsPrivateFalse());
                break;
            case "prt":
                journey_list_option_prt(model);
                break;
            default:
                List<Journey> journeyList = journeyService.JourneyForm_SQLQuery(this.journeyRequestForm);
                model.addAttribute("journey_list", journeyList);
                break;
            }
        model.addAttribute("journeyRequestForm", this.journeyRequestForm);
        return response;
    }

    @PostMapping("/travel/journey/profile")
    String journey_save(
            @ModelAttribute(name = "journey_form") Journey journey,
            @RequestParam(name = "id", defaultValue = "", required = false) Journey journey_fromDB,
            @RequestParam(name = "act", defaultValue = "", required = false) String action)
    {
        String response = "redirect:/travel/journey/list";

        if(!journey.optimize()){
            return response;
        }
        switch (action) {
            case "add":
                if(journeyService.create(journey)) {
                    response = "redirect:/travel/journey/profile?id=" + journey.getId() + "&act=obs";
                }
                break;
            case "upd":
               // FIXME: [SHITCODE] нужно получать из формы Journey.class-объект с полями, инициализированными с помощью передаваемого Journey.class-объекта через get-контроллер
                journey_fromDB.setTitle(journey.getTitle());
                journey_fromDB.setDescription(journey.getDescription());
                journey_fromDB.setIsPrivate(journey.getIsPrivate());

                if(journeyService.edit(journey_fromDB)){
                    response = "redirect:/travel/journey/profile?id="+ journey.getId() + "&act=obs";
                }
                break;
            default:
                break;
        }
        return response;

        /* TODO: [TRAVELING] функционал кода сверху сводится к сохранению в бд путешествия
            если его название содержит больше букв/цифр, чем title_length_min
            Возможно эту проверку и ErrorHandle можно вынести на HTML страницу используя JS (в backend не должны поступать не валидные формы)
         */
    }

    @GetMapping("/travel/journey/profile")
    String journey_create(
            Model model,
            @RequestParam(name = "id", required = false) Journey journey,
            @RequestParam(name = "act", required = false) String action
    ){
        String response = "redirect:/travel/journey/list";

        switch (action){
            case "obs":
                /*
                Если путешествие приватное то делаем проверку(является ли пользователь участником этого путешествия)
                В случае, если пользователь НЕ является участником или НЕ авторизован - break;
                 */
                if(journey.getIsPrivate()){
                        if(!(journeyService.hasJourneyRole(journey, JourneyRole.participant))){
                            break;
                        }
                }
                model.addAttribute("journey_form", journey);
                response = "Travelling/journey_profile.html";
                break;
            case "add":
                if(AuthenticationService.isAuthenticated()){
                    model.addAttribute("isNew", true);
                    model.addAttribute("journey_form", new Journey());
                    response = "Travelling/journey_profile_editor.html";
                }
                break;
            case "edt":
                if(journeyService.hasJourneyRole(journey,JourneyRole.editor))
                {
                    model.addAttribute("isNew", false);
                    model.addAttribute("journey_form", journey);
                    response = "Travelling/journey_profile_editor.html";
                }
                break;
            case "dlt":
                if(journeyService.delete(journey)) {
                    response = "redirect:/travel/journey/list";
                }
                break;
            default:
                break;
        }
        return response;
    }

    private void journey_list_option_prt(Model model){
        User user = AuthenticationService.getCurrentUser();
        if (user != null) {
            Integer id = user.getId();
            model.addAttribute("journey_list", travelRepository.findWhereUserIsParticipant(id));
            return;
        }
        model.addAttribute("journey_list", travelRepository.findByIsPrivateFalse());
    }
}
