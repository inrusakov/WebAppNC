package com.example.model.blog;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //GenerationType.AUTO)
    private Integer postId;

    //@JsonIgnore
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "blogid")
    private Blog blog;
//    private Tag tag;

    private Integer rating;
    private Integer views;
    private LocalDateTime publicationDate;
    private String header;

    @Column(columnDefinition="TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    private boolean archived = false;

    public User getAuthor(){
        return blog.getUser();
    }
}
