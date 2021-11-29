package com.example.repos;

import com.example.model.community.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findById(int id);

    Group findByName(String name);

    List<Group> findAll();

    @Query(value = "SELECT app_group.* FROM users_groups INNER JOIN app_group ON users_groups.group_id = app_group.group_id WHERE users_groups.user_id = :id", nativeQuery = true)
    java.util.Set<Group> findByUserId(@Param("id") int userId);
}
