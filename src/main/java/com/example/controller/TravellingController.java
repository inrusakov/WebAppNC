package com.example.controller;

import com.example.model.Traveling.Journey;
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

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.example.service.traveling.JourneyService.isValidJourneyId;
import static com.example.service.traveling.JourneyService.isValidJourneySearchTitle;

// TODO: [TRAVELING] Добавить выпадающее меню поиска с возможностью выбора параметров запроса к БД.
// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ) (визуальное изменение стиля оформления)
// TODO: [TRAVELING] Убрать возможность находить в поиске путешествия, недоступные пользователю.
@Controller
public class TravellingController {

    @Autowired
    private JourneyServiceImpl journeyService;

    @Autowired
    private TravelRepository travelRepository;

    @GetMapping("/travel/journey/list")
    String journey_list(
            Model model,
            @RequestParam(name = "req", defaultValue = "opn", required = false) String req,
            @RequestParam(name = "ttl", defaultValue = "", required = false) String title
    ) {
        String response = "Travelling/journey_list.html";

        switch (req){
            default:
            case "opn":
                model.addAttribute("journey_list", travelRepository.findByIsPrivateFalse());
                break;
            case "prt":
                journey_list_option_prt(model);
                break;
            case "ttl":
                journey_list_option_ttl(model,title);
                break;
            }
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

    private void journey_list_option_ttl(Model model,@NotNull String title){
        if(isValidJourneySearchTitle(title)) {
            if (isValidJourneyId(title)) {
                Journey ret = journeyService.findById(Integer.parseInt(title));
                if (ret != null) {
                    model.addAttribute("journey_list", List.of(ret));
                    return;
                }
            }
            model.addAttribute("journey_list", travelRepository.findByTitleContaining(title));
            return;
        }
        model.addAttribute("journey_list", new ArrayList<Journey>());
    }
}
