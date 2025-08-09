package com.poc.service;

import com.poc.dao.PostDao;
import com.poc.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> getAllPosts() {
        return postDao.getPosts();
    }

    public List<Post> getPublishedPosts() {
        return postDao.getPublishedPosts();
    }

    public List<Post> getNotPublishedPosts() {
        return postDao.getNotPublishedPosts();
    }

    public List<Post> getRandomPosts(int count) {
        return postDao.getRandomPosts(count);
    }


}
