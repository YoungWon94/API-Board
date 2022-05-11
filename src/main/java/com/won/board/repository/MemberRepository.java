package com.won.board.repository;

import com.won.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    boolean existsById(String id);

    @Query("select m from Member m where m.id = :id and m.isDeleted = false")
    Optional<Member> findByMemberId(String id);
}
