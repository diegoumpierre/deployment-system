package com.poc.service;

import com.poc.dao.UserDao;
import com.poc.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "User 1", true, null),
                new User(2L, "User 2", false, null)
        );
        when(userDao.getUsers()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getName());
    }

    @Test
    void testGetActiveUsers() {
        List<User> activeUsers = Arrays.asList(
                new User(1L, "User 1", true, null),
                new User(3L, "User 3", true, null)
        );
        when(userDao.getActiveUsers()).thenReturn(activeUsers);
        List<User> result = userService.getActiveUsers();
        assertTrue(result.stream().allMatch(User::isActive));
        assertEquals(2, result.size());
    }

    @Test
    void testGetInactiveUsers() {
        List<User> inactiveUsers = Arrays.asList(
                new User(2L, "User 2", false, null)
        );
        when(userDao.getInactiveUsers()).thenReturn(inactiveUsers);
        List<User> result = userService.getInactiveUsers();
        assertTrue(result.stream().noneMatch(User::isActive));
        assertEquals(1, result.size());
    }
}

