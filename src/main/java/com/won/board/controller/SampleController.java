package com.won.board.controller;

import com.won.board.result.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials = "true", originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/test" , produces = "application/json;charset=utf-8")
@Api(tags = "1.테스트")
@RequiredArgsConstructor
public class SampleController {


    @ApiOperation(value = "호출 테스트")
    @GetMapping("/test")
    public Response<?> test() {
        return Response.of(200,"test");
    }

}

