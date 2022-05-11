package com.won.board.repository;

import com.won.board.entity.Category;
import com.won.board.entity.Member;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("select c from Category c where c.categoryNo = :categoryNo and c.isDeleted = false")
    Optional<Category> findById(long categoryNo);

}
