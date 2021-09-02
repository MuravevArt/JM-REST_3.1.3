package com.example.jmspringboot.controllers;

import com.example.jmspringboot.models.User;
import com.example.jmspringboot.service.RoleService;
import com.example.jmspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String getCurrentUserInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "show";
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("newUser", new User());
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("thisUser", thisUser);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "adminPanel";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult,
                             @RequestParam(value = "index", required = false) Long[] index) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        if (index != null) {
            for (Long id : index) {
                user.addRole(roleService.findById(id));
            }
        } else {
            user.addRole(roleService.findById(2L));
        }
        userService.save(user);
        return "redirect:/admin";
    }

//    @GetMapping("/admin/users/{id}/edit")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("allRoles", roleService.getAllRoles());
//        model.addAttribute("user", userService.getById(id));
//        return "edit";
//    }

//    @PostMapping("/admin/users/{id}")
//    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                             @RequestParam(value = "index", required = false) Long[] index) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//
//        if (index != null) {
//            for (Long id : index) {
//                user.addRole(roleService.findById(id));
//            }
//        } else {
//            user.addRole(roleService.findById(2L));
//        }
//        userService.save(user);
//        return "redirect:/admin/users";
//    }

    @PostMapping("/admin/update")
    public String update(@ModelAttribute("newUser") User user, @RequestParam(value = "index", required = false) Long[] index) {
        if (index != null) {
            for (Long id : index) {
                user.addRole(roleService.findById(id));
            }
        } else {
            user.addRole(roleService.findById(2L));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
