package com.example.repos;

import com.example.model.community.GroupRole;
import com.example.model.community.Users_Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Users_GroupsRepository  extends JpaRepository<Users_Groups, Integer>  {
    @Query(value = "SELECT EXISTS(SELECT ug.* FROM Users_Groups ug " +
            "WHERE ug.group_id = :group_id AND ug.user_id = :user_id)",
            nativeQuery = true)
    Boolean isParticipant(@Param("group_id") Integer journey_id, @Param("user_id") Integer user_id);

    @Query(nativeQuery = true, value = "SELECT DISTINCT ug.user_role FROM users_groups ug " +
            "WHERE ug.group_id = :group_id AND ug.user_id = :user_id")
    Set<GroupRole> getRoles(@Param("group_id") Integer journey_id, @Param("user_id") Integer user_id);
}