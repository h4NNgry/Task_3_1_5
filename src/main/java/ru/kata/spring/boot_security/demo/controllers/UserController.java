package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserDetailService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UserDetailService userDetailService;

    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public void setUserService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/")
    public String start() {
        return "/login";
    }

    @GetMapping("user")
    public String updateUser(Principal principal, Model model) {
        User user = userDetailService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("rolesName") List<Role> listOfRoles) {

        userServiceImpl.addUser(user, listOfRoles);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String listUsers(Principal principal, Model model) {
        model.addAttribute("user", userDetailService.findByEmail(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("listUsers", userServiceImpl.listUsers());

        return "admin";
    }
    @GetMapping("admin/{id}/delete")
    public String delete(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userServiceImpl.removeUser(id);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "redirect:/admin";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam(value = "rolesName",
            defaultValue = "1") List<Role> listOfRoles) {
        userServiceImpl.updateUser(user, id, listOfRoles);
        return "redirect:/admin";
    }
}
