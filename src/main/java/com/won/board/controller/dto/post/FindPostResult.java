package com.won.board.controller.dto.post;

import com.won.board.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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


    //===== 생성 메서드 =====//

    public static FindPostResult from(@NonNull Post post) {
        FindPostResult result = new FindPostResult();
        result.setPostNo(post.getPostNo());
        result.setIsNotice(post.getIsNotice());
        result.setTitle(post.getTitle());
        result.setContents(post.getContents());
        result.setWriterMember(new WriterMemberDto(post.getMember()));
        return result;
    }


    //===== 내부 클라스 =====//

}
