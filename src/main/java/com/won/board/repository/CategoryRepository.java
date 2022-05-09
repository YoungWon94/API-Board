package com.won.board.repository;

import com.won.board.entity.Category;
import com.won.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
