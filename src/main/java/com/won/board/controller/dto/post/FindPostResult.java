package com.won.board.controller.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.won.board.entity.Comment;
import com.won.board.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 게시글 단건 조회 결과
 */
@ApiModel("게시글 단건 결과")
@Getter @Setter(AccessLevel.PRIVATE)
public class FindPostResult {

    @ApiModelProperty(required = true, value = "게시글 번호", position = 10)
    private Long postNo; //게시글 번호

    @ApiModelProperty(required = true, value = "공지글 여부", position = 20)
    private Boolean isNotice;

    @ApiModelProperty(required = true, value = "게시글 제목", position = 30)
    private String title;

    @ApiModelProperty(required = true, value = "게시글 내용", position = 40)
    private String contents;

    @ApiModelProperty(required = true, value = "게시글 작성자", position = 50)
    private WriterMemberDto writerMember;

    @ApiModelProperty(required = true, value = "댓글목록", position = 60)
    private List<CommentDto> commentList;


    //===== 생성 메서드 =====//

    public static FindPostResult of(@NonNull Post post, List<CommentDto> commentDtoList) {
        FindPostResult result = new FindPostResult();
        result.setPostNo(post.getPostNo());
        result.setIsNotice(post.getIsNotice());
        result.setTitle(post.getTitle());
        result.setContents(post.getContents());
        result.setWriterMember(new WriterMemberDto(post.getMember()));
        result.setCommentList(commentDtoList);
        return result;
    }

    //===== 내부 클라스 =====//

    /**
     * 댓글 정보
     */
    @ApiModel("댓글 정보")
    @Getter
    public static class CommentDto {

        @ApiModelProperty(required = true, value = "댓글 번호", position = 10)
        private Long commentNo;

        @ApiModelProperty(required = false, value = "상위댓글 번호", position = 20)
        private Long parentCommentNo;

        @ApiModelProperty(required = true, value = "작성자id", position = 30)
        private String memberId;

        @ApiModelProperty(required = true, value = "작성자id", position = 40)
        private String contents;

        @ApiModelProperty(required = true, value = "작성일(yyyy-MM-dd'T'HH:mm:ss)", position = 50)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdTimestamp;

        @ApiModelProperty(required = false, value = "수정일(yyyy-MM-dd'T'HH:mm:ss)", position = 60)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedTimestamp;

        public CommentDto(@NonNull Comment comment) {
            this.commentNo = comment.getCommentNo();
            if(comment.getParentComment() != null) {
                this.parentCommentNo = comment.getParentComment().getCommentNo();
            }
            this.memberId = comment.getMember().getId();
            this.contents = comment.getContents();
            this.createdTimestamp = comment.getCreatedTimestamp();
            this.updatedTimestamp = comment.getUpdatedTimestamp();
        }
    }

}
