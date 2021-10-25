package com.example.model.blog;

import com.example.model.Tag;
import com.example.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
//@Entity
//@Table(name = "Comment")
public class Comment {
// === CONSTANTS ===
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
// === ATTRIBUTES ===
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "comment_id")
    private Integer id;

    // Unidirectional link, т.к. у пользователя нет необходимости в просмотре всех своих комментраиев
    // Hibernate создаст таблицу соединений между user и comment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Getter
    @Setter
    @Column(
            name = "comment_body",
            nullable = false
    )
    private String commentBody;

    // TODO: Задать значение в методе сохранения комментария в БД = new Timestamp(Calendar.getInstance().getTime().getTime());
    @Column(
            name = "creation_time",
            nullable = false
    )
    private Timestamp creationTime;

   // @Transient
   @Column(
           name = "edit",
           nullable = false
   )
    private Integer edit = 0;
// TODO: Добавить комментирование комментариев. Ниже написана основа этого функционала

//    @Column(name = "isRootComment", columnDefinition = "boolean default true")
//    private boolean isRootComment;

    @Column(name = "layer", columnDefinition = "integer default 0")
    private Integer layer;

// TODO: Добавить систему рейтинга для комментариев. Ниже написана основа этого функционала

    @Column(
            name = "rating",
            nullable = false
    )
    private Integer rating = 0;

    @OneToMany(targetEntity=User.class, fetch=FetchType.LAZY)
    private List<User> upVoters;

    @OneToMany(targetEntity=User.class, fetch=FetchType.LAZY)
    private List<User> downVoters;

    @Column(
            name = "like_amount",
            nullable = false
    )
    private Integer likeAmount = 0;

    @OneToMany(targetEntity=User.class,  fetch=FetchType.EAGER)
    private List<User> likes;

// === CONSTRUCTORS ===

// === @OVERRIDE METHODS ===
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return "Comment{" +
                "comment_id=" + id +
                ", user_id=" + user.getId() +
                ", creation_time=" + dateFormat.format(creationTime.toLocalDateTime()) +
                ", commentBody=" + commentBody +
                '}';
    }

// === METHODS ===

    // TODO: Реализовать функционал в CommentService

    public void putLike(User user){
        likeAmount++;
        likes.add(user);
    }

    public void removeLike(User user){
        likeAmount--;
        likes.remove(user);
    }

    public void increaseRating(User user){
        rating++;
       // upVoters.add(user);
    }

    public void decreaseRating(User user){
        rating--;
        //downVoters.add(user);
    }

    //public Comment getRootComment(){return null;}
    //public Comment getParentComment(){return null;}
    //public List<Comment> getSubComments(){return null;}
    //public void addSubComment(){}
}
