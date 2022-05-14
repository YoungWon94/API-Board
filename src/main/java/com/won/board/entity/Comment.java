package com.won.board.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity @DynamicUpdate
@Table(name = "t_comment", schema = "board")
@SequenceGenerator(name = "t_comment_comment_no_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_comment_comment_no_seq")
    @Column(name = "comment_no", nullable = false)
    private Long commentNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_no", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_no")
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Column(name = "contents", nullable = false, length = 550) @Size(min=1, max=500)
    private String contents;


    //===== 연관관계 메서드 =====//

    public void setMember(Member member) {
        this.member = member;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    //===== 생성 메서드 =====//


    public static Comment of(
            Comment parentComment,
            @NonNull Post post,
            @NonNull Member member,
            @NonNull String contents
    ) {
        Comment comment = new Comment();
        comment.setParentComment(parentComment);
        comment.setPost(post);
        comment.setMember(member);
        comment.setContents(contents);
        return comment;
    }

    //===== 비즈니스 메서드 =====//


    /**
     * 댓글 내용 변경
     */
    public void changeContents(@NonNull String contents) {
        setContents(contents);
    }
}