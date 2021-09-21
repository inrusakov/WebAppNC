package com.example.model.blog;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    private int postId;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Blog blog;
    private String header;

}
