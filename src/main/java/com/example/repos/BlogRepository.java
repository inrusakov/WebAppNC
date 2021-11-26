package com.example.repos;

import com.example.model.User;
import com.example.model.blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findAll();
    Optional<Blog> findByBlogID(Integer blogID);
}
