package com.won.board.controller;

import com.won.board.controller.dto.member.RegisterMemberParam;
import com.won.board.exception.CommonException;
import com.won.board.facade.MemberFacade;
import com.won.board.result.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(allowCredentials = "true", originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/member" , produces = "application/json;charset=utf-8")
@Api(tags = "1.회원")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @ApiOperation(value = "회원등록")
    @PostMapping("")
    public Response<?> registerMember(
            @RequestBody @Valid RegisterMemberParam param
            ) throws CommonException {
        memberFacade.registerMember(param);
        return Response.from(200);
    }

}

