package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/users")
public class AdminRESTController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping()
    public List<User> listUsers() {

        return userServiceImpl.listUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {

        return userServiceImpl.getUserById(id);
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {

        return userServiceImpl.addUser(user);
    }

    @PatchMapping()
    public void editUser(@RequestBody User user) {

        userServiceImpl.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable int id) {

        userServiceImpl.removeUser(id);
    }
}
