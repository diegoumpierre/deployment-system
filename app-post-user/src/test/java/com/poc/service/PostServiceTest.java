package com.poc.service;

import com.poc.dao.PostDao;
import com.poc.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {
    private PostDao postDao;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postDao = mock(PostDao.class);
        postService = new PostService(postDao);
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1L, "Post 1", "Content 1", true),
                new Post(2L, "Post 2", "Content 2", false)
        );
        when(postDao.getPosts()).thenReturn(posts);
        List<Post> result = postService.getAllPosts();
        assertEquals(2, result.size());
        assertEquals("Post 1", result.get(0).getTitle());
    }

    @Test
    void testGetPublishedPosts() {
        List<Post> published = Arrays.asList(
                new Post(1L, "Post 1", "Content 1", true)
        );
        when(postDao.getPublishedPosts()).thenReturn(published);
        List<Post> result = postService.getPublishedPosts();
        assertTrue(result.stream().allMatch(Post::isPublished));
        assertEquals(1, result.size());
    }

    @Test
    void testGetNotPublishedPosts() {
        List<Post> notPublished = Arrays.asList(
                new Post(2L, "Post 2", "Content 2", false)
        );
        when(postDao.getNotPublishedPosts()).thenReturn(notPublished);
        List<Post> result = postService.getNotPublishedPosts();
        assertTrue(result.stream().noneMatch(Post::isPublished));
        assertEquals(1, result.size());
    }

    @Test
    void testGetRandomPosts() {
        List<Post> randomPosts = Arrays.asList(
                new Post(3L, "Post 3", "Content 3", true)
        );
        when(postDao.getRandomPosts(1)).thenReturn(randomPosts);
        List<Post> result = postService.getRandomPosts(1);
        assertEquals(1, result.size());
        assertEquals("Post 3", result.get(0).getTitle());
    }

}

