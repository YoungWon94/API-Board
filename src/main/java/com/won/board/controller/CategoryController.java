package com.won.board.controller;

import com.won.board.controller.dto.category.CreateCategoryParam;
import com.won.board.controller.dto.category.FindAllCategoryResult;
import com.won.board.controller.dto.category.ModifyCategoryParam;
import com.won.board.exception.CommonException;
import com.won.board.facade.CategoryFacade;
import com.won.board.result.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

    @ApiOperation(value = "카테고리 수정")
    @PutMapping("/{categoryNo}")
    public Response<?> modifyCategory(
            @PathVariable @Valid @NotNull @Positive Long categoryNo,
            @RequestBody @Valid ModifyCategoryParam param
            ) throws CommonException {
        categoryFacade.modifyCategory(categoryNo, param);
        return Response.from(200);
    }


}

