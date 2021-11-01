package com.example.repos;

import com.example.model.User;
import com.example.model.community.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findByGroupId(int groupId);

    Group findByGroupName(String groupName);

    List<Group> findAll();

    @Query(value = "SELECT app_group.* FROM users_groups INNER JOIN app_group ON users_groups.group_id = app_group.group_id WHERE users_groups.user_id = :id", nativeQuery = true)
    java.util.List<Group> findByUserId(@Param("id") int userId);
}
