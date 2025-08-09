package com.poc.dao;

import com.poc.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private PostDao postDao;

    public UserDao(PostDao postDao) {
        this.postDao = postDao;
    }

    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with some dummy data
        users.add(new User(1L, "User 1", true, postDao.getRandomPosts(2)));
        users.add(new User(2L, "User 2", true, postDao.getRandomPosts(3)));
        users.add(new User(3L, "User 3", false, postDao.getRandomPosts(1)));
        users.add(new User(4L, "User 4", true, postDao.getRandomPosts(6)));
        users.add(new User(5L, "User 5", false, postDao.getRandomPosts(5)));
        users.add(new User(6L, "User 6", true, postDao.getRandomPosts(1)));


    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getActiveUsers() {
        Predicate<User> activeUserPredicate = user -> user.isActive();
        return users.stream().filter(activeUserPredicate).collect(Collectors.toList());
    }

    public List<User> getInactiveUsers() {
        Predicate<User> inactiveUserPredicate = user -> !user.isActive();
        return users.stream().filter(inactiveUserPredicate).collect(Collectors.toList());
    }

}
