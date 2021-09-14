package com.example.jmspringboot.controllers;

import com.example.jmspringboot.exceptionHandling.NoSuchUsersException;
import com.example.jmspringboot.models.Role;
import com.example.jmspringboot.models.User;
import com.example.jmspringboot.service.RoleService;
import com.example.jmspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> showAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(Model model) {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getPrincipalUser() {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(thisUser, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showOneUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new NoSuchUsersException("User with id " + id + " not found in DB");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new NoSuchUsersException("User with id " + id + " not found in DB");
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
