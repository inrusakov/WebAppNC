package com.example.controller;

import com.example.model.Traveling.Journey;
import com.example.model.User;
import com.example.repos.TravelRepository;
import com.example.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

// TODO: [TRAVELING] Убрать возможность создавать названия, состоящие из системных символов.
// TODO: [TRAVELING] Убрать возможность пользоваться системными исмволами в запросах.
// TODO: [TRAVELING] Добавить выпадающее меню поиска с возможностью выбора параметров запроса к БД.
// TODO: [TRAVELING] Добавить возможность создавать новые путешествия.
// TODO: [TRAVELING] Добавить возможность редактировать параметры путешествия.
// TODO: [TRAVELING] Реализовать зависимость функционала от статуса путешествия ( планируется / проходит / закончилось ).

@Controller
public class TravellingController {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/travel/journey/list")
    String journey_create(
            @RequestParam Map<String, String > form,
            @RequestParam Journey journey){

        this.travelRepository.save(journey);
        return "Travelling/journey_list.html";
    }

    @GetMapping("travel/journey/{journeyId}")
    String journey_profile(Model model, @PathVariable Journey journeyId) {
        model.addAttribute("journey", journeyId);
        return "Travelling/journey_profile.html";
    }

    private void journey_list_option_prt(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // FIXME: [TRAVELING] убрать запрос user_id через БД (настройки UserDetails в SpringSecurity)
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userRepository.findByEmail(username); // Этот запрос уже сделал SpringSecurity, но без возможности получить user_id
            Integer id = user.getId();
            model.addAttribute("journey_list", travelRepository.findWhereUserIsParticipant(id));
        }else{
            model.addAttribute("journey_list", travelRepository.findByIsPrivateFalse());
        }
    }
    private void journey_list_option_ttl(Model model, String title){
        if(title != null && !title.isEmpty()) {
            if (isJourneyID(title)) {
                Optional<Journey> ret = travelRepository.findById(Integer.parseInt(title));
                if (ret.isPresent()) {
                    List<Journey> ret_list = new ArrayList<>();
                    ret_list.add(ret.get());
                    model.addAttribute("journey_list", ret_list);
                }else{
                    model.addAttribute("journey_list", travelRepository.findByTitleContaining(title));
                }
            } else {
                model.addAttribute("journey_list", travelRepository.findByTitleContaining(title));
            }
        }else
            model.addAttribute("journey_list", new ArrayList<Journey>());
    }
    private boolean isJourneyID(String string){
        try{
            Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
