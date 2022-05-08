package com.won.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_member", schema = "board")
@SequenceGenerator(name = "t_member_member_no_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_member_member_no_seq")
    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @Column(name = "id", nullable = false)
    private String id; //회원Id

    @Column(name = "password", nullable = false)
    private String password; //비밀번호

    @Column(name = "name", nullable = false)
    private String name; //이름

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; //휴대폰번호

    @Column(name = "email", nullable = true)
    private String email; //이메일주소

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday; //생년월일

}