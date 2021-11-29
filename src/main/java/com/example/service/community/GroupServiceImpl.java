package com.example.service.community;

import com.example.model.User;
import com.example.model.community.Group;
import com.example.model.community.GroupRole;
import com.example.repos.GroupRepository;
import com.example.repos.Users_GroupsRepository;
import com.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService
{
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    Users_GroupsRepository UGRepository;

//    @Override
//    public boolean isGroupExist(Group group) {
//        if(group == null) return false;
//
//        Optional<Group> optional = groupRepository.findById(group.getId());
//        return optional.isPresent();
//    }
//
//    @Override
//    public Set<Group> getGroupsByUser(User user) {
//        if (user == null){
//            return new HashSet<>();
//        }else {
//            Set<Users_Groups> u = user.getUserGroups();
//            Set<Group> ret = new HashSet<>();
//            for(Users_Groups f : u){
//                ret.add(f.getGroup());
//            }
//            return ret;
//        }
//    }
//
    @Override
    public void create(Group group) {
        groupRepository.save(group);
    }
//
//    @Override
//    public void create(Group group, User user) {
//            group.addUser(user, GroupRole.participant);
//            group.addUser(user, GroupRole.editor);
//            group.addUser(user, GroupRole.admin);
//            groupRepository.save(group);
//    }
//
//    @Override
//    public void delete(Group group) {
//        if(hasGroupRole(group, GroupRole.admin)){
//            groupRepository.delete(group);
//        }
//    }
//
//    @Override
//    public void update(Group group) {
//        if(hasGroupRole(group, GroupRole.editor)){
//            groupRepository.save(group);
//        }
//    }
//
    @Override
    public boolean isParticipant(Group group, User user) {
        if(group == null || user == null){
            return false;
        }
        return UGRepository.isParticipant(group.getId(), user.getId());
    }
//
//    @Override
//    public void addUser(Group group, User user, GroupRole role) {
//        if(hasGroupRole(group, GroupRole.editor)){
//            group.addUser(user, role);
//            groupRepository.save(group);
//        }
//    }
//
//    @Override
//    public void addUser(Group group, User user) {
//        addUser(group,user,GroupRole.participant);
//    }
//
//    @Override
//    public void addUsers(Group group, Collection<User> users) {
//        if(hasGroupRole(group, GroupRole.editor)){
//            for (User u : users){
//                group.addUser(u, GroupRole.participant);
//            }
//            groupRepository.save(group);
//        }
//    }
//
//    @Override
//    public void addUsers(Group group, User... users) {
//        if(hasGroupRole(group, GroupRole.editor)){
//            for (User u : users){
//                group.addUser(u, GroupRole.participant);
//            }
//            groupRepository.save(group);
//        }
//    }
//
//    @Override
//    public void removeUser(Group group, User user) {
////        if(hasGroupRole(group, GroupRole.editor)){
////            group.removeUser(user);
////            groupRepository.save(group);
////        }
//    }
//
//    @Override
//    public void removeUsers(Group group, Collection<User> users) {
////        if(hasGroupRole(group, GroupRole.editor)){
////            group.removeUsers(users);
////            groupRepository.save(group);
////        }
//    }
//
//    @Override
//    public void removeUsers(Group group, User... users) {
////        if(hasGroupRole(group, GroupRole.editor)){
////            group.removeUsers(users);
////            groupRepository.save(group);
////        }
//    }
//
//    @Override
//    public Set<User> getByGroupRole(Group group, GroupRole role) {
////        return users_groupsRepository.findUsersInGroupByRole(group.getId(), role.toString());
//        return null;
//    }
//
//    @Override
//    public boolean hasGroupRole(Group group, User user, GroupRole role) {
//        return true;
//    }
//
//    @Override
//    public boolean hasGroupRole(Group group, GroupRole role) {
//        return hasGroupRole(group, AuthenticationService.getCurrentUser(), role);
//    }
//
    @Override
    public Set<GroupRole> getRoles(Group group, User user) {
        if (group == null || user == null) return null;
        return UGRepository.getRoles(group.getId(), user.getId());
    }

    @Override
    public Set<GroupRole> getRoles(Group group) {
        User user = AuthenticationService.getCurrentUser();
        return getRoles(group, user);
    }
}
