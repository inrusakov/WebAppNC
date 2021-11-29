package com.example.service.community;

import com.example.model.User;
import com.example.model.community.Group;
import com.example.model.community.GroupRole;

import java.util.Set;

public interface GroupService {
//    boolean isGroupExist(Group group);
//    Set<Group> getGroupsByUser(User user);
    void create(Group group);
//    void create(Group group, User user);
//    void delete(Group group);
//    void update(Group group);

    boolean isParticipant(Group group, User user);
//    void addUser(Group group, User user);
//    void addUser(Group group, User user, GroupRole role);
//    void addUsers(Group group, Collection<User> users);
//    void addUsers(Group group, User ... users);
//    void removeUser(Group group, User user);
//    void removeUsers(Group group, Collection<User> users);
//    void removeUsers(Group group, User ... users);
//    Set<User> getByGroupRole(Group group, GroupRole role);

//    boolean hasGroupRole(Group group, User user, GroupRole role);
//    boolean hasGroupRole(Group group, GroupRole role);
    Set<GroupRole> getRoles(Group group, User user);
    Set<GroupRole> getRoles(Group group);
}
