package com.example.repos;

import com.example.model.blog.Blog;
import com.example.model.blog.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByPostId(Integer postId, Pageable pageable);
    Page<Post> findAllByBlog(Blog blog, Pageable pageable);

    List<Post> findAllByBlog(Blog blog);
    List<Post> findAllByPostId(Integer postId);
}
