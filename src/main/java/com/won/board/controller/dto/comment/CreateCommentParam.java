package com.won.board.controller.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel("댓글 생성 파라미터")
@Getter
public class CreateCommentParam {

    @ApiModelProperty(required = true, value = "회원ID", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String memberId; //TODO : 토큰으로 세션조회하는걸로 변경

    @ApiModelProperty(required = true, value = "게시글 번호", position = 20)
    @NotNull @Positive
    private Long postNo;

    @ApiModelProperty(required = false, value = "상위 댓글 번호", position = 30)
    @Positive
    private Long parentCommentNo;

    @ApiModelProperty(required = true, value = "내용", position = 40)
    @NotBlank @Size(min = 2, max = 500)
    private String contents;

}
