package com.won.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_category", schema = "board")
@SequenceGenerator(name = "t_category_category_no_seq", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter(AccessLevel.PRIVATE)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_category_category_no_seq")
    @Column(name = "category_no", nullable = false)
    private Long categoryNo;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    //===== 연관관계 메서드 =====//

    public void setMember(@NonNull Member member) {
        this.member = member;
    }

    //===== 생성 메서드 =====//

    public static Category of(@NonNull String name, @NonNull Member member) {
        Category category = new Category();
        category.setName(name);
        category.setMember(member);
        return category;
    }

    //===== 비즈니스 메서드 =====//

    public void rename(@NonNull String name) {
        this.name = name;
    }

}