package com.example.model.community;

import com.example.model.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "app_group")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer groupId;
    private String groupName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", users=" + users +
                '}';
    }
}
