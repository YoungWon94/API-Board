package com.won.board.controller.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel("게시글 생성 파라미터")
@Getter
public class CreatePostParam {

    @ApiModelProperty(required = true, name = "회원ID", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String memberId; //TODO : 토큰으로 세션조회하는걸로 변경

    @ApiModelProperty(required = true, name = "카테고리 번호", position = 20)
    @NotNull @Positive
    private Long categoryNo;

    @ApiModelProperty(required = true, name = "제목", position = 30)
    @NotBlank @Size(min = 2, max = 50)
    private String title;

    @ApiModelProperty(required = true, name = "내용", position = 40)
    @NotBlank @Size(min = 2, max = 5000)
    private String contents;

}
