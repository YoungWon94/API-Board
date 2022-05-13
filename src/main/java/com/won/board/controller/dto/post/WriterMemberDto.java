package com.won.board.controller.dto.post;

import com.won.board.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;


@ApiModel("게시글 작성자(회원)정보")
@Getter
public  class WriterMemberDto {

    @ApiModelProperty(required = true, value = "회원 번호", position = 10)
    private Long memberNo;

    @ApiModelProperty(required = true, value = "회원ID", position = 20)
    private String id; //회원Id

    @ApiModelProperty(required = true, value = "회원이름", position = 30)
    private String name; //이름

    @ApiModelProperty(required = true, value = "회원타입", position = 40)
    private String roleType;

    public WriterMemberDto(@NonNull Member member) {
        this.memberNo = member.getMemberNo();
        this.id = member.getId();
        this.name = member.getName();
        this.roleType = member.getRoleType().name();
    }
}

