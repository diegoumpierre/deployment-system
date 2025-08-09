package com.poc.service;

import com.poc.dao.UserDao;
import com.poc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    public List<User> getActiveUsers() {
        return userDao.getActiveUsers();
    }

    public List<User> getInactiveUsers() {
        return userDao.getInactiveUsers();
    }
}
