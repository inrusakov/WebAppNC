package com.example.controller;

import com.example.model.CustomUserDetails;
import com.example.model.Traveling.Journey;
import com.example.model.User;
import com.example.repos.TravelRepository;
import com.example.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

// TODO: [TRAVELING] Добавить выпадающее меню поиска с возможностью выбора параметров запроса к БД.
// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ) (визуальное изменение стиля оформления)
// TODO: [TRAVELING] Убрать возможность находить в поиске путешествия, недоступные пользователю.
@Controller
public class TravellingController {

    @Autowired
    private TravelRepository travelRepository;

    @GetMapping("/travel/journey/list")
    String journey_list(
            Model model,
            @RequestParam(name = "req", defaultValue = "opn", required = false) String req,
            @RequestParam(name = "ttl", defaultValue = "", required = false) String title
    ) {
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
        return "Travelling/journey_list.html";
    }

    @PostMapping("/travel/journey/profile")
    String journey_save(
            @ModelAttribute(name = "journey_form") Journey journey,
            @RequestParam(name = "id", defaultValue = "", required = false) Journey journey_fromDB,
            @RequestParam(name = "act", defaultValue = "", required = false) String action)
    {
            switch (action) {
                case "add":
                    if(journey.optimize_and_validate()){
                        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if (principal instanceof CustomUserDetails){
                            // Add Owner to participants // TODO: [TRAVELING] Учасники путешествия - учасники группы
                            User owner = ((CustomUserDetails) principal).getUser();
                            journey.addParticipants(owner);
                            // Saving
                            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
                            travelRepository.save(journey);
                            return "redirect:/travel/journey/profile?id="+ journey.getId() +"&act=obs";
                        }
                    }
                    // TODO: [TRAVELING] ErrorHandler ?
                    break;
                case "upd":
                    if(journey.optimize_and_validate()) {
                        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if (
                                principal instanceof CustomUserDetails &&
                                journey_fromDB.isParticipant(((CustomUserDetails) principal).getUser())
                        ){
                            // FIXME: [SHITCODE] нужно получать из формы Journey.class-объект с полями, инициализированными с помощью передаваемого Journey.class-объекта через get-контроллер
                            journey_fromDB.setTitle(journey.getTitle());
                            journey_fromDB.setDescription(journey.getDescription());
                            journey_fromDB.setIsPrivate(journey.getIsPrivate());

                            // FIXME: [SHITCODE] исправить код ниже. Функционал: обновлять, если есть в базе данных. Не допустить создания дубликата после удаления.
                            if(travelRepository.findById(journey_fromDB.getId()).isPresent()) {
                                travelRepository.save(journey_fromDB);
                            }
                            return "redirect:/travel/journey/profile?id="+ journey.getId() +"&act=obs";
                        }
                    }
                    break;
                default:
                    break;
            }
            // TODO: [TRAVELING] ErrorHandler ?
        return "redirect:/travel/journey/list";

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
        switch (action){
            case "obs":
                if(journey != null) {
                    if(journey.getIsPrivate()){
                        if(!(isRedactorOfJourney(journey))){
                            break;
                        }
                    }
                    model.addAttribute("journey_form", journey);
                    return "Travelling/journey_profile.html";
                }
                break;
            case "add":
                model.addAttribute("isNew", true);
                model.addAttribute("journey_form", new Journey());
                return "Travelling/journey_profile_editor.html";
            case "edt":
                if(journey != null && isRedactorOfJourney(journey))
                {
                    model.addAttribute("isNew", false);
                    model.addAttribute("journey_form", journey);
                    return "Travelling/journey_profile_editor.html";
                }
                break;
            case "dlt":
                if(journey != null && isRedactorOfJourney(journey)){
                    travelRepository.delete(journey);
                }
                break;
            default:
                break;
        }
        return "redirect:/travel/journey/list";
    }

    private void journey_list_option_prt(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Integer id = ((CustomUserDetails) principal).getUserId();
            model.addAttribute("journey_list", travelRepository.findWhereUserIsParticipant(id));
            return;
        }
        model.addAttribute("journey_list", travelRepository.findByIsPrivateFalse());
    }

    private void journey_list_option_ttl(Model model,@NotNull String title){
        if(Journey.isValidTitleSearch(title)) {
            if (Journey.isValidJourneyId(title)) {
                Optional<Journey> ret = travelRepository.findById(Integer.parseInt(title));
                if (ret.isPresent()) {
                    model.addAttribute("journey_list", List.of(ret.get()));
                    return;
                }
            }
            model.addAttribute("journey_list", travelRepository.findByTitleContaining(title));
            return;
        }
        model.addAttribute("journey_list", new ArrayList<Journey>());
    }

    private boolean isRedactorOfJourney(@NotNull Journey journey){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(
                principal instanceof CustomUserDetails && journey.isParticipant(((CustomUserDetails) principal).getUser())
        ){
            return true;
        }
        return false;
    }
}
