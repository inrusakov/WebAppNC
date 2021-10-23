package com.example.controller.Events;


import com.example.dao.EventDAO;
import com.example.model.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class MainEventsController {

    private final EventDAO eventDAO;

    @Autowired
    public MainEventsController(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    /**
     * Main page with all events.
     * @param model
     * @return
     */

    @GetMapping()
    public String index(Model model){
        model.addAttribute("events", eventDAO.index());
        return "events/index";
    }

    /**
     * Page for event with id equals {id}.
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("event", eventDAO.show(id));
        return "events/show";
    }

    /**
     * Page for adding a new event.
     * @param event
     * @return
     */

    @GetMapping("/new")
    public String newEvent(@ModelAttribute("event") Event event, Model model){
        model.addAttribute("orgName", eventDAO.getOrganization());
        return  "events/new";
    }

    /**
     * Post query from user to server with information about new event
     * @param event
     * @param bindingResult - Checks if there were errors with binding Event fields with inserted values.
     * @return
     */

    @PostMapping()
    public String create(@ModelAttribute("updatedEvent") @Valid Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "events/new";

        eventDAO.save(event);

        return "redirect:/events";
    }

    @GetMapping("/edit/edit")
    public String edit(Model model){
        model.addAttribute("events", eventDAO.edit());
        return "events/edit/edit";
    }

    @GetMapping("/edit/modify/{id}")
    public String modify(@PathVariable("id") int id, Model model, @ModelAttribute("updatedEvent") Event updatedEvent){
        model.addAttribute("orgName", eventDAO.getOrganization());
        model.addAttribute("event", eventDAO.show(id));
        return "/events/edit/modify";
    }

    @PostMapping("/edit/edit")
    public String modifyEvent(@ModelAttribute("updatedEvent") @Valid Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "events/new";

        eventDAO.update(event);
        return "redirect:/events";
    }
}
