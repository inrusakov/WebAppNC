package com.example.model.blog;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int postId;
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "post_id")
//    private Blog blog;
    private String header;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

//    public Blog getBlog() {
//        return blog;
//    }
//
//    public void setBlog(Blog blog) {
//        this.blog = blog;
//    }
}
