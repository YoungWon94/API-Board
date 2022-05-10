package com.won.board.controller.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("카테고리 이름변경 파라미터")
@Getter
public class RenameCategoryParam {

    @ApiModelProperty(required = true, name = "회원ID", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String memberId; //TODO : 토큰으로 세션조회하는걸로 변경

    @ApiModelProperty(required = true, name = "카테고리명", position = 20)
    @NotBlank @Size(min = 2, max = 20)
    private String name;

}
