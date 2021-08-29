package com.example.jmspringboot.service;

import com.example.jmspringboot.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(Long id);

    User getByLogin(String email);

    void save(User user);

    void delete(Long id);
}
