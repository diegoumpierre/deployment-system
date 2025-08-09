package com.poc.dao;

import com.poc.domain.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class PostDao {

    public PostDao(){

    }

    private List<Post> posts = new ArrayList<>();


    @PostConstruct
    public void init() {
        // Initialize with some dummy data
        posts.add(new Post(1L, "Post 1", "Content of Post 1", true));
        posts.add(new Post(2L, "Post 2", "Content of Post 2", true));
        posts.add(new Post(3L, "Post 3", "Content of Post 3", true));
        posts.add(new Post(4L, "Post 4", "Content of Post 4", false));
        posts.add(new Post(5L, "Post 5", "Content of Post 5", false));
        posts.add(new Post(6L, "Post 6", "Content of Post 6", false));
    }


    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> getPublishedPosts() {
        Predicate<Post> publishedPostsPredicate = post -> post.isPublished() == true;
        return posts.stream().filter(publishedPostsPredicate).collect(Collectors.toList());
    }

    public List<Post> getNotPublishedPosts() {
        Predicate<Post> publishedPostsPredicate = post -> post.isPublished() == false;
        return posts.stream().filter(publishedPostsPredicate).collect(Collectors.toList());
    }

    public List<Post> getRandomPosts(int count) {
        return posts.stream()
                .sorted()
                .limit(count)
                .collect(Collectors.toList());
    }

}
