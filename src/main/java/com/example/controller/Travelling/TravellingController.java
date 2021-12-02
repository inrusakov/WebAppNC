package com.example.controller.Travelling;

import com.example.model.Traveling.Journey.Journey;
import com.example.model.Traveling.Journey.JourneyRole;
import com.example.service.traveling.JourneyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ) (визуальное изменение стиля оформления)
@Controller
@RequestMapping("/journey")
public class TravellingController {

    @Autowired
    private JourneyServiceImpl journeyService;

    @GetMapping("")
    String journey_list(
            Model model,
            @RequestParam(name = "q", required = false) String title
    ) {
        String response = "Travelling/journey_list.html";
        return response;
    }

    @GetMapping("/add")
    String journey_add(){
        String response = "Travelling/journey_list.html";
        return response;
    }

    @GetMapping("/profile/{id}")
    String journey_create(
            Model model,
            @PathVariable(name = "id", required = false) Journey journey
    ){
        String response = "Travelling/journey_list.html";

        if(journey == null){
            response = "Travelling/journey_notFound.html";
        }else{
             /*
             Если путешествие приватное то делаем проверку(является ли пользователь участником этого путешествия)
             В случае, если пользователь НЕ является участником или НЕ авторизован - break;
             */
            if(journey.getIsPrivate()){
                if(!(journeyService.hasJourneyRole(journey, JourneyRole.participant))){
                    response = "Travelling/journey_accessDenied.html";
                }
            }
        }
        return response;
    }
}
