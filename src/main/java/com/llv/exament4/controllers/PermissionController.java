package com.llv.exament4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.llv.exament4.models.Permission;
import com.llv.exament4.services.PermissionService;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    public ModelAndView getAllPermissions() {
        ModelAndView mav = new ModelAndView("permissionList");
        mav.addObject("permissions", permissionService.getAllPermissions());
        return mav;
    }

    @GetMapping("/permission/{id}")
    public ModelAndView getPermissionById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("permissionDetail");
        mav.addObject("permission", permissionService.getPermission(id));
        return mav;
    }

    @GetMapping("/permission/new")
    public ModelAndView addPermissionPage() {
        ModelAndView mav = new ModelAndView("permissionForm");
        mav.addObject("permission", new Permission());
        return mav;
    }

    @PostMapping("/permission/save")
    public ModelAndView savePermission(@ModelAttribute Permission permission) {
        permissionService.createPermission(permission);
        return new ModelAndView("redirect:/permissions");
    }

    @GetMapping("/permission/edit/{id}")
    public ModelAndView editPermissionPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("permissionForm");
        mav.addObject("permission", permissionService.getPermission(id));
        return mav;
    }

    @GetMapping("/permission/delete/{id}")
    public ModelAndView deletePermission(@PathVariable("id") Long id) {
        permissionService.deletePermission(id);
        return new ModelAndView("redirect:/permissions");
    }
}
