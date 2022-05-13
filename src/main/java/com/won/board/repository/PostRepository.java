package com.won.board.repository;

import com.won.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select p from Post p where p.category.categoryNo = :categoryNo and p.isDeleted = false")
    List<Post> findByCategoryNo(long categoryNo);

    @Query("select p from Post p where p.postNo = :postNo and p.isDeleted = false")
    Optional<Post> findByPostNo(long postNo);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Post p set p.isDeleted = true where p.postNo = :postNo")
    int deleteByPostNo(long postNo);

}
