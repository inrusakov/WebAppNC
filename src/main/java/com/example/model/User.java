package com.example.model;

//import com.example.model.Address;

import com.example.model.blog.Blog;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.List;


@Entity
// This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Size(min=5, message = "At least 5 characters")
    private String email;
    @Size(min=8, message = "At least 8 characters")
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private File pic;
    @OneToMany(targetEntity=Tag.class,  fetch=FetchType.EAGER)
    private List<Tag> tag; //enum hobby
    //private Address userAddress;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private Blog blog;

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

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }

    public File getPic() {
        return pic;
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

    /*


    public Address getUserAddress(){
        return userAddress;
    }

    public void setUserAddress(userAddress){
        this.userAddress = userAddress;
    }
     */

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role + // ", address=" + userAddress.toString()+
                '}';
    }
}

enum Role {USER, COMPANY, ADMIN}
