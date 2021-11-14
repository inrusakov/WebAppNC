package com.example.controller.community;

import com.example.model.User;
import com.example.model.community.Group;
import com.example.service.AuthenticationService;
import com.example.service.community.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @GetMapping(value = "/userGroups")
    public String getGroups(Model model) {
        User user = AuthenticationService.getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user)
                    .addAttribute("group", user.getGroups());
            return "userGroups";
        }
        return "redirect:";
    }

    @PostMapping(value = "/userGroups")
    public RedirectView addGroup(@RequestParam String name) {
        User user = AuthenticationService.getCurrentUser();
        if (user != null) {
            Group group = new Group();
            group.setName(name);
            group.addParticipants(user);
            groupService.create(group);
        }
        return new RedirectView("/userGroups");
    }
/*
    @GetMapping(value = "/Group/{groupId}")
    public String currentGroup(Model model, @PathVariable("groupId") Integer groupId) {
        Group group = groupRepository.findByGroupId(groupId);
        model.addAttribute("group", group);
        return"Group";
    }

 */
}