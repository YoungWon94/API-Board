package com.won.board.controller.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@ApiModel("회원등록 파라미터")
@Getter
public class RegisterMemberParam {

    @ApiModelProperty(required = true, value = "id", position = 10)
    @NotBlank @Size(min = 5, max = 20)
    private String id;

    @ApiModelProperty(required = true, value = "비밀번호", position = 20)
    @NotBlank @Size(min = 10, max = 30) //TODO : 해시 & 암호화
    private String password;

    @ApiModelProperty(required = true, value = "이름", position = 30)
    @NotBlank @Size(min = 1, max = 40)
    private String name; //이름

    @ApiModelProperty(required = true, value = "휴대폰번호('-'포함)", position = 40)
    @NotBlank @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}")
    private String phoneNumber; //휴대폰번호

    @ApiModelProperty(required = false, value = "이메일주소", position = 50)
    @NotBlank @Email
    private String email; //이메일주소

    @ApiModelProperty(required = true, value = "생년월일(yyyy-MM-dd)", position = 60)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthday; //생년월일

}
