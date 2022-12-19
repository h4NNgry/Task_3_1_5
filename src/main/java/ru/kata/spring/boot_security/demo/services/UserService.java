package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    public void addUser(User user, List<Role> listOfRoles);
    public List<User> listUsers();
    public void updateUser(User user, int id, List<Role> listOfRoles);
    public void removeUser(int id);
    public User getUserById(int id);
}
