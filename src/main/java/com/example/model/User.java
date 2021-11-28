package com.example.model;


import com.example.model.blog.Blog;
import com.example.model.community.Group;
import com.example.model.community.Users_Groups;
import com.example.model.blog.Comment;
import com.example.model.blog.PostComment;
import com.example.model.geoposition.Address;
import com.example.model.org.Organisation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity(name = "app_User")
// This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(unique=true)
    @Size(min=5, message = "At least 5 characters")
    private String email;
    @Size(min=8, message = "At least 8 characters")
    private String password;
    private String firstName;
    private String lastName;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private boolean active;
    private boolean wasBanned;
    private File pic;
    @OneToMany(targetEntity=Tag.class,  fetch=FetchType.EAGER)
    private List<Tag> tag;
    @ManyToOne(fetch = FetchType.LAZY)
    private Address userAddress;

    @JsonIgnore
    @OneToOne(optional = true, mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Blog blog;

    @Size(min=8, message = "At least 8 characters")
    @Column(name = "password_BCrypt")
    private String password_encoded;

    @OneToMany(mappedBy="user")
    private Set<Users_Groups> userGroups = new HashSet<>();

    @ManyToMany(mappedBy = "likes", cascade = CascadeType.ALL)
    private Set<PostComment> postLikes;

    @ManyToMany(mappedBy = "upVoters", cascade = CascadeType.ALL)
    private Set<PostComment> postUpvoters;

    @ManyToMany(mappedBy = "downVoters", cascade = CascadeType.ALL)
    private Set<PostComment> postDownvoters;

    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Set<Role> role) {
        this.roles = role;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }

    public File getPic() {
        return pic;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setWasBanned(boolean wasBanned) {
        this.wasBanned = wasBanned;
    }

    public boolean isWasBanned() {
        return wasBanned;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Address getUserAddress(){
        return userAddress;
    }

    public void setUserAddress(Address userAddress){
        this.userAddress = userAddress;
    }

    public String getPassword_encoded() {
        return password_encoded;
    }

    public void setPassword_encoded(String password_encoded) {
        this.password_encoded = password_encoded;
    }

//    public List<Group> getGroups() {
//        List<Group> ret = new ArrayList<>();
//        for(Users_Groups groupUser : this.groups){
//            ret.add(groupUser.getGroup());
//        }
//        return ret;
//    }

    public Set<Users_Groups> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<Users_Groups> userGroups) {
        this.userGroups = userGroups;
    }

    public HashSet<Group> getGroups(){
        HashSet<Group> ret = new HashSet<>();
        for(Users_Groups u : this.userGroups){
            ret.add(u.getGroup());
        }
        return ret;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", address=" + userAddress.toString() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof User)){
            return false;
        }
        return ((User) obj).getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

