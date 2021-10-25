package com.example.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "post_comment")
public class PostComment extends Comment{
    @OneToOne
    @JoinColumn(
            name = "post_id",
            nullable = false
    )
    private Post post;

    @ManyToOne(
           // cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_comment_id")
    private PostComment parentComment;


    //    // Bidirectional link, из-за реализации функции обновления рейтинга комментария
//    @OneToMany(
//            mappedBy = "parentComment" // поле другого класса, в котором мы будем хранить ссылку про этот класс
//            //cascade = CascadeType.ALL,
//           // orphanRemoval = true
//    )
    @OneToMany(targetEntity=PostComment.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "sub_comment_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @ElementCollection(targetClass=PostComment.class)
//    @Column(name="subComment", nullable=false)
//    @CollectionTable(name="subcomments", joinColumns= {@JoinColumn(name="comment_id")})
    private List<PostComment> subComments;

    public void addSubComment(PostComment pc){
        subComments.add(pc);
    }

    public void removeSubComment(PostComment pc){
        subComments.remove(pc);
    }

    public int getTotalRating(){
        int total = getRating();
        for(PostComment comment : subComments)
            total += comment.getTotalRating();
        return total;
    }
}
