package com.example.model.blog;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
public class Blog {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "blogid")
    private int blogID;

   // @JsonIgnore
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "blogid")
    private User user;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Post> posts;

    @JsonIgnore
    public List<Post> getNotArchivedPosts(){
        return posts.stream().filter(post -> !post.isArchived()).collect(Collectors.toList());
    }
}
