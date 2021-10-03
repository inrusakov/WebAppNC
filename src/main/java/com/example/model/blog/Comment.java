package com.example.model.blog;

import com.example.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor  // POJO class

@Entity
@Table(name = "Comment")
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
    @Column(
            name = "user_id",
            nullable = false
    )
    private User user;
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

// TODO: Добавить комментирование комментариев. Ниже написана основа этого функционала

//    // Bidirectional link, из-за реализации функции обновления рейтинга комментария
//    @OneToMany(
//            mappedBy = "parentComment", // поле другого класса, в котором мы будем хранить ссылку про этот класс
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @Column(name = "sub_comment_id")
//    private java.util.List<Comment> subComments = new java.util.ArrayList<>();
//
//    @ManyToOne(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(name = "parent_comment_id")
//    private Comment parentComment = null;
//
//    @Column(name = "isRootComment")
//    private boolean isRootComment = true;

// TODO: Добавить систему рейтинга для комментариев. Ниже написана основа этого функционала

//    @Column(
//            name = "rating",
//            nullable = false
//    )
//    private Integer rating = 0;

//    @Column(
//            name = "like_amount",
//            nullable = false
//    )
//    private Integer likeAmount = 0;

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

    //public void putLike(){}
    //public void removeLike(){}
    //public Comment getRootComment(){return null;}
    //public Comment getParentComment(){return null;}
    //public List<Comment> getSubComments(){return null;}
    //public void addSubComment(){}
}
