package com.poc.dao;

import com.poc.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {
    private PostDao postDao;

    @BeforeEach
    void setUp() {
        postDao = new PostDao();
        postDao.init();
    }

    @Test
    void testGetPosts() {
        List<Post> posts = postDao.getPosts();
        assertEquals(6, posts.size(), "Should return all posts");
    }

    @Test
    void testGetPublishedPosts() {
        List<Post> publishedPosts = postDao.getPublishedPosts();
        assertEquals(3, publishedPosts.size(), "Should return 3 published posts");
        assertTrue(publishedPosts.stream().allMatch(Post::isPublished), "All should be published");
    }

    @Test
    void testGetNotPublishedPosts() {
        List<Post> notPublishedPosts = postDao.getNotPublishedPosts();
        assertEquals(3, notPublishedPosts.size(), "Should return 3 not published posts");
        assertTrue(notPublishedPosts.stream().noneMatch(Post::isPublished), "None should be published");
    }
}