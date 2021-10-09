package com.example.model.blog;

import com.example.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int blogID;

    @OneToOne(optional = false, mappedBy = "blog")
    private User user;

//    @OneToMany(mappedBy = "post_id", fetch = FetchType.EAGER)
//    private ArrayList<Post> posts;

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public ArrayList<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(ArrayList<Post> posts) {
//        this.posts = posts;
//    }
}
