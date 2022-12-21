package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserDetailService;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserRESTController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserRESTController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @GetMapping()
    public User getUser(Principal principal) {
        return userDetailService.findByEmail(principal.getName());
    }
}
