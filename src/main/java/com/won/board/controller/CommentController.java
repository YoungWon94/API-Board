package com.won.board.controller;

import com.won.board.controller.dto.comment.CreateCommentParam;
import com.won.board.controller.dto.comment.DeleteCommentParam;
import com.won.board.controller.dto.comment.ModifyCommentParam;
import com.won.board.controller.dto.post.*;
import com.won.board.exception.CommonException;
import com.won.board.facade.CommentFacade;
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
@Api(tags = "4.댓글")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @ApiOperation(value = "댓글 생성")
    @PostMapping("/comment")
    public Response<?> createComment(
            @RequestBody @Valid CreateCommentParam param
    ) throws CommonException {
        commentFacade.createComment(param);
        return Response.from(200);
    }

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/comment/{commentNo}")
    public Response<?> modifyComment(
            @PathVariable @Valid @NotNull @Positive Long commentNo,
            @RequestBody @Valid ModifyCommentParam param
    ) throws CommonException {
        commentFacade.modifyComment(commentNo, param);
        return Response.from(200);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/comment/{commentNo}")
    public Response<?> deleteComment(
            @PathVariable @Valid @NotNull @Positive Long commentNo,
            @ModelAttribute @Valid DeleteCommentParam param
            ) throws CommonException {
        commentFacade.deleteComment(commentNo, param);
        return Response.from(200);
    }



}
