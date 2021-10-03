package com.example.repos;

import com.example.model.blog.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Соединение с БД
public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    public static final int MOST_POPULAR_COMMENT_AMOUNT = 1;
//    List<Comment> findMostPopular();

//    List<Comment> findSubComments(Integer ParentCommentId);

}
