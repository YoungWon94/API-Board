package com.won.board.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @DynamicUpdate
@Table(name = "t_post", schema = "board")
@SequenceGenerator(name = "t_post_post_no_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Post extends BaseEntity  {

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

    //===== 연관관계 메서드 =====//

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    //===== 생성 메서드 =====//

    public static Post of(
            @NotNull Category category,
            @NonNull Member member,
            boolean isNotice,
            @NonNull String title,
            @NonNull String contents
            ) {
        Post post = new Post();
        post.setCategory(category);
        post.setMember(member);
        post.setIsNotice(isNotice);
        post.setTitle(title);
        post.setContents(contents);
        return post;
    }

}