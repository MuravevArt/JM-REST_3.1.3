package com.example.jmspringboot.dao;


import com.example.jmspringboot.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getById(Long id);

    User getByLogin(String email);

    void save(User user);

    void update(User user);

    void delete(Long id);
}
