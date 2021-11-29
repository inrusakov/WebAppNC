package com.example.model.community;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor

@Entity(name = "app_group")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "group_id")
    private Integer id;

    private String name = "";

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Users_Groups> usersGroups = new HashSet<>();

    public Group(String name, HashSet<Users_Groups> usersGroups) {
        this.name = name;
        this.usersGroups = usersGroups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Users_Groups> getUsersGroups() {
        return usersGroups;
    }

    public void setUsersGroups(Set<Users_Groups> usersGroups) {
        this.usersGroups = usersGroups;
    }

    public Set<User> getParticipants(){
        Set<User> ret = new HashSet<>();
        for(Users_Groups u : this.usersGroups){
            ret.add(u.getUser());
        }
        return ret;
    }

    public void addParticipants(User ... users){
        for (User u : users){
            this.usersGroups.add(new Users_Groups(this, u, GroupRole.participant));
        }
    }
    public boolean containsUser(User user){
        if(user == null){
            return false;
        }
        return getParticipants().contains(user);
    }
    public void addUser(User user, GroupRole role){
        this.usersGroups.add(new Users_Groups(this, user, role));
    }
}
