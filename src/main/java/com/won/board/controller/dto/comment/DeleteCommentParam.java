package com.won.board.controller.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("댓글 삭제 파라미터")
@Getter
public class DeleteCommentParam {

    @ApiModelProperty(required = true, value = "회원ID", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String memberId; //TODO : 토큰으로 세션조회하는걸로 변경
}
