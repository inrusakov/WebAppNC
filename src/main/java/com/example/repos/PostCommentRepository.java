package com.example.repos;

import com.example.model.blog.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Соединение с БД
public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

//    public static final int MOST_POPULAR_COMMENT_AMOUNT = 1;
//    List<Comment> findMostPopular();

//    List<Comment> findSubComments(Integer ParentCommentId);

        @Query(value = "SELECT * FROM post_comment WHERE post_id = ?1 ORDER BY rating DESC",
                nativeQuery = true)
       List<PostComment> findAllByPostId(Integer postId);

        @Query(value = "SELECT * FROM post_comment WHERE post_id = ?1 AND layer=0 ORDER BY rating DESC",
                nativeQuery = true)
        List<PostComment> findAllRootComments(Integer postId);
}
