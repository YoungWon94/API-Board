package com.won.board.repository;

import com.won.board.entity.Comment;
import com.won.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select c from Comment c where c.commentNo = :commentNo and c.isDeleted = false")
    Optional<Comment> findByCommentNo(long commentNo);


    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Comment c set c.isDeleted = true where c.commentNo = :commentNo and c.isDeleted = false")
    int deleteByCommentNo(long commentNo);

}
