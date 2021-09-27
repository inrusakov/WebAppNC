package com.example.controller;


import com.example.dao.EventDAO;
import com.example.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventDAO eventDAO;

    @Autowired
    public EventsController(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("events", eventDAO.index());
        return "events/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("event", eventDAO.show(id));
        return "events/show";
    }

    @GetMapping("/new")
    public String newEvent(@ModelAttribute("event") Event event){
        return  "events/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("event") @Valid Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "events/new";

        eventDAO.save(event);

        return "redirect:/events";
    }
}
