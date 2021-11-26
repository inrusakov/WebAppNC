package com.example.model.blog;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "post_comment")
public class PostComment extends Comment{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "post_id",
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;


    //    // Bidirectional link, из-за реализации функции обновления рейтинга комментария
//    @OneToMany(
//            mappedBy = "parentComment" // поле другого класса, в котором мы будем хранить ссылку про этот класс
//            //cascade = CascadeType.ALL,
//           // orphanRemoval = true
//    )

    @Column(
            name = "like_amount",
            nullable = false
    )
    private Integer likeAmount = 0;

    //@OneToMany(targetEntity=User.class,  fetch=FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_comment_likes",
            joinColumns = @JoinColumn(name = "post_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> likes;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_comment_upvoters",
            joinColumns = @JoinColumn(name = "post_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> upVoters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_comment_downvoters",
            joinColumns = @JoinColumn(name = "post_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> downVoters;



    @ManyToOne(
            // cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_comment_id")
    @JsonIgnore
    private PostComment parentComment;

    //@OneToMany(targetEntity=PostComment.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "sub_comment_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @ElementCollection(targetClass=PostComment.class)
//    @Column(name="subComment", nullable=false)
//    @CollectionTable(name="subcomments", joinColumns= {@JoinColumn(name="comment_id")})
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<PostComment> subComments;

    @JsonIgnore
    public List<PostComment> getSortedSubcomments(){
        subComments.sort(Comparator.comparing(PostComment::getTotalRating));
        Collections.reverse(subComments);
        return subComments;
    }

    public void addSubComment(PostComment pc){
        subComments.add(pc);
    }

    public void removeSubComment(PostComment pc){
        subComments.remove(pc);
    }

    @JsonIgnore
    public int getTotalRating(){
        int total = getRating();
        for(PostComment comment : subComments)
            total += comment.getTotalRating();
        return total;
    }

    public void putLike(User user){
        likeAmount++;
        likes.add(user);
    }

    public void removeLike(User user){
        likeAmount--;
        likes.remove(user);
    }
}
