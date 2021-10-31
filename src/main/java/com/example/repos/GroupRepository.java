package com.example.repos;

import com.example.model.community.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findByGroupName(String groupName);

    List<Group> findAll();
}
