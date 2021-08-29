package com.example.jmspringboot.service;

import com.example.jmspringboot.models.Role;

import java.util.List;


public interface RoleService {

    List<Role> getAllRoles();

    Role findById(Long id);

    void saveRole(Role role);
}
