package com.llv.exament4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.llv.exament4.models.Group;
import com.llv.exament4.services.GroupService;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public ModelAndView getAllGroups() {
        ModelAndView mav = new ModelAndView("groupList");
        mav.addObject("groups", groupService.getAllGroups());
        return mav;
    }

    @GetMapping("/group/{id}")
    public ModelAndView getGroupById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("groupDetail");
        mav.addObject("group", groupService.getGroup(id));
        return mav;
    }

    @GetMapping("/group/new")
    public ModelAndView addGroupPage() {
        ModelAndView mav = new ModelAndView("groupForm");
        mav.addObject("group", new Group());
        return mav;
    }

    @PostMapping("/group/save")
    public ModelAndView saveGroup(@ModelAttribute Group group) {
        groupService.createGroup(group);
        return new ModelAndView("redirect:/groups");
    }

    @GetMapping("/group/edit/{id}")
    public ModelAndView editGroupPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("groupForm");
        Group group = groupService.getGroup(id);
        mav.addObject("group", group);
        return mav;
    }

    @GetMapping("/group/delete/{id}")
    public ModelAndView deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
        return new ModelAndView("redirect:/groups");
    }
}
