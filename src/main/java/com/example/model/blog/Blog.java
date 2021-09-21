package com.example.model.blog;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int blogID;

    @OneToOne(optional = false, mappedBy = "blog")
    private User user;

    @OneToMany(mappedBy = "post_id", fetch = FetchType.EAGER)
    private ArrayList<Post> posts;
}
