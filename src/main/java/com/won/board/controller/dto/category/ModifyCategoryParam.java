package com.won.board.controller.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("카테고리 수정 파라미터")
@Getter
public class ModifyCategoryParam {

    @ApiModelProperty(required = true, value = "회원ID", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String memberId; //TODO : 토큰으로 세션조회하는걸로 변경

    @ApiModelProperty(required = true, value = "카테고리명", position = 20)
    @NotBlank @Size(min = 2, max = 20)
    private String name;

    @ApiModelProperty(required = true, value = "사용여부", position = 30)
    @NotNull
    private Boolean isUsed;

}
