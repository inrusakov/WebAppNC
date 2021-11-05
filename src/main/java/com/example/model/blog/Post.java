package com.example.model.blog;

import com.example.model.User;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "blogid", nullable=false, insertable=false, updatable=false)
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

    public User getAuthor(){
        return blog.getUser();
    }
}
