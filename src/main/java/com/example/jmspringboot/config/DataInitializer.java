package com.example.jmspringboot.config;

import com.example.jmspringboot.models.Role;
import com.example.jmspringboot.models.User;
import com.example.jmspringboot.service.RoleService;
import com.example.jmspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user = new User();
        user.setEmail("artem@mail.ru");
        user.setPassword("artem");
        user.setName("Artem");
        user.setSurname("Muravev");
        user.setAge((byte) 26);
        user.addRole(role1);

        User user1 = new User();
        user1.setEmail("anna@mail.ru");
        user1.setPassword("anna");
        user1.setName("Anna");
        user1.setSurname("Muraveva");
        user1.setAge((byte) 30);
        user1.addRole(role2);

        User user2 = new User();
        user2.setEmail("test@mail.ru");
        user2.setPassword("test");
        user2.setName("Test");
        user2.setSurname("Testovich");
        user2.setAge((byte) 3);
        user2.addRole(role1);
        user2.addRole(role2);

        userService.save(user);
        userService.save(user1);
        userService.save(user2);
    }
}
