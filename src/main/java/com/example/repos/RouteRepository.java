package com.example.repos;

import com.example.model.blog.Post;
import com.example.model.geoposition.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    Page<Route> findAll(Pageable pageable);
}
