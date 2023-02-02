package com.llv.exament4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.llv.exament4.models.Group;
import com.llv.exament4.models.Permission;
import com.llv.exament4.models.User;
import com.llv.exament4.services.GroupService;
import com.llv.exament4.services.PermissionService;
import com.llv.exament4.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("userList");
        List<User> allUsers = userService.getAllUsers();
        mav.addObject("users", allUsers);
        return mav;
    }

    @GetMapping("/user/{id}")
    public ModelAndView getUserById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("userForm");

        User user = userService.getUser(id);
        mav.addObject("user", user);

        List<Group> allGroups = groupService.getAllGroups();
        mav.addObject("groups", allGroups);

        List<Permission> allPermissions = permissionService.getAllPermissions();

        for (Permission permission : allPermissions) {
            if( user.getPermissions().contains(permission)){
                permission.setHasPermission(true);
            }
        }

        mav.addObject("permissions", allPermissions);


        return mav;
    }

    @GetMapping("/user/new")
    public ModelAndView addUserPage() {

        ModelAndView mav = new ModelAndView("userForm");
        mav.addObject("user", new User());
        
        List<Group> allGroups = groupService.getAllGroups();
        mav.addObject("groups", allGroups);
        return mav;
    }

    @PostMapping("/user/save")
    public ModelAndView saveUser(@ModelAttribute User user) {
        userService.createUser(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/user/edit/{id}")
    public ModelAndView editUserPage(@PathVariable("id") Long id) {


        ModelAndView mav = new ModelAndView("userForm");

        User user = userService.getUser(id);
        mav.addObject("user", user);

        List<Group> allGroups = groupService.getAllGroups();
        mav.addObject("groups", allGroups);

        List<Permission> allPermissions = permissionService.getAllPermissions();

        for (Permission permission : allPermissions) {
            if( user.getPermissions().contains(permission)){
                permission.setHasPermission(true);
            }
        }

        mav.addObject("permissions", allPermissions);

        return mav;
    }

    @GetMapping("/user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }
}
