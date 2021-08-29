package com.example.jmspringboot.dao;


import com.example.jmspringboot.models.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    Role findById(Long id);

    void saveRole(Role role);
}
