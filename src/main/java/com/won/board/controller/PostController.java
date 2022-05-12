package com.won.board.controller;

import com.won.board.controller.vo.category.CreateCategoryParam;
import com.won.board.controller.vo.category.FindAllCategoryResult;
import com.won.board.controller.vo.category.ModifyCategoryParam;
import com.won.board.controller.vo.post.CreatePostParam;
import com.won.board.controller.vo.post.FindPostByCategoryResult;
import com.won.board.exception.CommonException;
import com.won.board.facade.CategoryFacade;
import com.won.board.facade.PostFacade;
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
@RequestMapping(value = "" , produces = "application/json;charset=utf-8")
@Api(tags = "3.게시글")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @ApiOperation(value = "게시글 생성")
    @PostMapping("/post")
    public Response<?> createPost(
            @RequestBody @Valid CreatePostParam param
    ) throws CommonException {
        postFacade.createPost(param);
        return Response.from(200);
    }

    @ApiOperation(value = "게시글 목록 조회", notes = "카테고리의 게시글 조회")
    @GetMapping("/category/{categoryNo}/posts")
    public Response<FindPostByCategoryResult> findPostByCategory(
            @PathVariable @Valid @NotNull @Positive Long categoryNo
    ) throws CommonException {
        FindPostByCategoryResult result =  postFacade.findPostByCategory(categoryNo);
        return Response.from(result);
    }


}
