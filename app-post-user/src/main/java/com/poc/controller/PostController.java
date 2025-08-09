package com.poc.controller;

import com.poc.domain.Post;
import com.poc.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/published")
    public List<Post> getPublishedPosts() {
        return postService.getPublishedPosts();
    }

    @GetMapping("/not-published")
    public List<Post> getNotPublishedPosts() {
        return postService.getNotPublishedPosts();
    }

    @GetMapping("/random")
    public List<Post> getRandomPosts(@RequestParam(defaultValue = "1") int count) {
        return postService.getRandomPosts(count);
    }
}
