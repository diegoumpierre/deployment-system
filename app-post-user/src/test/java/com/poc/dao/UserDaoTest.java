package com.poc.dao;

import com.poc.domain.User;
import com.poc.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao userDao;
    private PostDao postDao;

    @BeforeEach
    void setUp() {
        postDao = Mockito.mock(PostDao.class);
        Mockito.when(postDao.getRandomPosts(Mockito.anyInt()))
                .thenAnswer(invocation -> {
                    int count = invocation.getArgument(0);
                    Post post = new Post();
                    return Arrays.asList(new Post[count]);
                });
        userDao = new UserDao(postDao);
        userDao.init();
    }

    @Test
    void testGetUsers() {
        List<User> users = userDao.getUsers();
        assertEquals(6, users.size(), "Should return all users");
    }

    @Test
    void testGetActiveUsers() {
        List<User> activeUsers = userDao.getActiveUsers();
        assertTrue(activeUsers.stream().allMatch(User::isActive), "All should be active");
        assertEquals(4, activeUsers.size(), "Should return 4 active users");
    }

    @Test
    void testGetInactiveUsers() {
        List<User> inactiveUsers = userDao.getInactiveUsers();
        assertTrue(inactiveUsers.stream().noneMatch(User::isActive), "None should be active");
        assertEquals(2, inactiveUsers.size(), "Should return 2 inactive users");
    }
}