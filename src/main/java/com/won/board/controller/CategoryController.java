package com.won.board.controller;

import com.won.board.controller.vo.category.CreateCategoryParam;
import com.won.board.controller.vo.category.FindAllCategoryResult;
import com.won.board.controller.vo.member.RegisterMemberParam;
import com.won.board.exception.CommonException;
import com.won.board.facade.CategoryFacade;
import com.won.board.facade.MemberFacade;
import com.won.board.result.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(allowCredentials = "true", originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/category" , produces = "application/json;charset=utf-8")
@Api(tags = "2.카테고리")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @ApiOperation(value = "카테고리 생성")
    @PostMapping("")
    public Response<?> createCategory(
            @RequestBody @Valid CreateCategoryParam param
            ) throws CommonException {
        categoryFacade.createCategory(param);
        return Response.from(200);
    }

    @ApiOperation(value = "전체 카테고리 조회")
    @GetMapping("")
    public Response<FindAllCategoryResult> findAllCategory(
    ) throws CommonException {
        FindAllCategoryResult result = categoryFacade.findAllCategory();
        return Response.from(result);
    }

}

