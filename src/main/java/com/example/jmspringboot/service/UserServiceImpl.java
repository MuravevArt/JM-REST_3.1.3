package com.example.jmspringboot.service;

import com.example.jmspringboot.dao.RoleDao;
import com.example.jmspringboot.dao.UserDao;
import com.example.jmspringboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void save(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        if (user.getRoles().size() == 0) {
            user.addRole(roleDao.findById(2L));
        }
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        if (user.getRoles().size() == 0) {
            user.addRole(roleDao.findById(2L));
        }
        User oldUser = getById(user.getId());
        if (!oldUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getByLogin(String email) {
        return userDao.getByLogin(email);
    }
}
