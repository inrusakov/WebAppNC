package com.example.model.blog;

import com.example.model.Tag;
import com.example.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass

public class Comment {
// === CONSTANTS ===
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
// === ATTRIBUTES ===


    // Unidirectional link, т.к. у пользователя нет необходимости в просмотре всех своих комментраиев
    // Hibernate создаст таблицу соединений между user и comment
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    //@OnDelete(action = OnDeleteAction.CASCADE)
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

    @Column(name = "layer", columnDefinition = "integer default 0")
    private Integer layer;

    @Column(
            name = "rating",
            nullable = false
    )
    private Integer rating = 0;

    public void increaseRating(User user){
        rating++;
    }

    public void decreaseRating(User user){
        rating--;
    }

    public String getFormattedCreationTime(){
        return new SimpleDateFormat(DATE_FORMAT).format(creationTime);
    }

// === @OVERRIDE METHODS ===
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return "Comment{" +
                ", user_id=" + user.getId() +
                ", creation_time=" + dateFormat.format(creationTime.toLocalDateTime()) +
                ", commentBody=" + commentBody +
                '}';
    }

// === METHODS ===

    //public Comment getRootComment(){return null;}
    //public Comment getParentComment(){return null;}
    //public List<Comment> getSubComments(){return null;}
    //public void addSubComment(){}
}
