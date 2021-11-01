package com.example.model.community;

import com.example.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "app_group")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer groupId;
    private String groupName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_groups",
        joinColumns = {
            @JoinColumn(name ="group_id", referencedColumnName = "groupId")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
        })
    private List<User> users;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if (this.users == null) {
            this.users = new LinkedList<>();
        }
        this.users.add(user);
    }

    public String getUsernames(){
        String s = "";
        for(User u: users){
            if(u.getFirstName() != null && !Objects.equals(u.getFirstName(), "")){
                if(s.equals("")){
                    s = u.getFirstName();
                }else {
                    s = s + ", " + u.getFirstName();
                }
            } else {
                if(s.equals("")){
                    s = u.getEmail();
                } else {
                    s = s + ", " + u.getEmail();
                }
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", users=" + users +
                '}';
    }
}
