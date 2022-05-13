package com.won.board.controller.vo.post;

import com.won.board.entity.Member;
import com.won.board.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@ApiModel("게시글 작성자정보")
@Getter
public  class WriterMemberDto {

    private Long memberNo;
    private String id; //회원Id
    private String name; //이름
    private String roleType;

    public WriterMemberDto(@NonNull Member member) {
        this.memberNo = member.getMemberNo();
        this.id = member.getId();
        this.name = member.getName();
        this.roleType = member.getRoleType().name();
    }
}

