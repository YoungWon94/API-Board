package com.won.board.repository;

import com.won.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select p from Post p where p.category.categoryNo = :categoryNo and p.isDeleted = false")
    List<Post> findByCategoryNo(long categoryNo);

    @Query("select p from Post p where p.postNo = :postNo and p.isDeleted = false")
    Optional<Post> findByPostNo(long postNo);

}
