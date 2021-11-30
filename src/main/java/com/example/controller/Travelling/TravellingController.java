package com.example.controller.Travelling;

import com.example.model.Traveling.Journey.Journey;
import com.example.model.Traveling.Journey.JourneyRole;
import com.example.model.User;
import com.example.service.AuthenticationService;
import com.example.service.traveling.JourneyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ) (визуальное изменение стиля оформления)
@Controller
@RequestMapping("/travel/journey")
public class TravellingController {

    @Autowired
    private JourneyServiceImpl journeyService;

    @GetMapping("")
    String journey_list(
            Model model,
            @RequestParam(name = "ttl", required = false) String ttl
    ) {
        String response = "Travelling/journey_list.html";

        User user = AuthenticationService.getCurrentUser();
        List<Journey> journeyList_prt = journeyService.getJourney_isParticipant(ttl);
        model.addAttribute("journey_list", journeyList_prt);
        return response;
    }

    @PostMapping("/profile")
    String journey_save(
            @ModelAttribute(name = "journey_form") Journey journey,
            @RequestParam(name = "id", required = false) Journey journey_fromDB,
            @RequestParam(name = "act", defaultValue = "", required = false) String action)
    {
        String response = "redirect:/travel/journey/list";

        if(!journey.optimize()){
            return response;
        }
        switch (action) {
            case "add":
                if(journeyService.create(journey) != null) {
                    response = "redirect:/travel/journey/profile?id=" + journey.getId() + "&act=obs";
                }
                break;
            case "upd":
                BeanUtils.copyProperties(journey, journey_fromDB,"id");
                if(journeyService.edit(journey_fromDB) != null){
                    response = "redirect:/travel/journey/profile?id="+ journey.getId() + "&act=obs";
                }
                break;
            default:
                break;
        }
        return response;
    }

    @GetMapping("/profile")
    String journey_create(
            Model model,
            @RequestParam(name = "id", required = false) Journey journey,
            @RequestParam(name = "act", defaultValue = "", required = false) String action
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
                if(journeyService.hasJourneyRole(null, JourneyRole.creator)){
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
}
