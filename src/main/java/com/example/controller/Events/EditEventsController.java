package com.example.controller.Events;

import com.example.dao.EventDAO;
import com.example.model.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/events/edit")
public class EditEventsController {

    private final EventDAO eventDAO;

    @Autowired
    public EditEventsController(EventDAO eventDAO){ this.eventDAO = eventDAO;}

    @GetMapping("edit")
    public String edit(Model model){
        model.addAttribute("orgName", eventDAO.getOrganization());
        model.addAttribute("events", eventDAO.edit());
        return "events/edit/edit";
    }

    @GetMapping("modify/{id}")
    public String modify(@PathVariable("id") int id, Model model, @ModelAttribute("event") Event updatedEvent){
        model.addAttribute("orgName", eventDAO.getOrganization());
        model.addAttribute("event", eventDAO.show(id));
        return "/events/edit/modify";
    }

    @PostMapping("edit")
    public String modifyEvent(@ModelAttribute("updatedEvent") @Valid Event event, BindingResult bindingResult,
                              @RequestParam("action") String action, HttpServletRequest request){
        if(bindingResult.hasErrors())
            return "events/new";
        if(action.equals("Modify")) {
            eventDAO.update(event);
        }
        if(action.equals("Delete")) {
            eventDAO.delete(Integer.parseInt(request.getParameter("eventId")));
        }
        return "redirect:/events/edit/edit";
    }

    @GetMapping("delete/{id}")
    public String deleteEvent(@PathVariable("id") int id){
        eventDAO.delete(id);

        return "events/edit/edit";
    }
}
