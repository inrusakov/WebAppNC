package com.example.model.blog;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //GenerationType.AUTO)
    private Integer postId;
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "post_id")
//    private Blog blog;
//    private Tag tag;
//    private Comment comment;
//    private Place place;
    private Integer rating;
    private Integer views;
    private LocalDateTime publicationDate;
    private String header;

    @Column(columnDefinition="TEXT")
    private String content;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
