package com.won.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity @DynamicUpdate
@Table(name = "t_post", schema = "board")
@SequenceGenerator(name = "t_post_post_no_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_post_post_no_seq")
    @Column(name = "post_no", nullable = false)
    private Long postNo; //게시글 번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_no", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Column(name = "is_notice", nullable = false)
    private Boolean isNotice = false;

    @Column(name = "title", nullable = false, length = 55) @Size(min=2, max=55)
    private String title;

    @Column(name = "contents", nullable = false, length = 5100) @Size(min=2, max=5100)
    private String contents;

}