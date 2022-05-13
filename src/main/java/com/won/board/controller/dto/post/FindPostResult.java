package com.won.board.controller.dto.post;

import com.won.board.entity.Post;
import io.swagger.annotations.ApiModel;
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

    private Long postNo; //게시글 번호
    private Boolean isNotice;
    private String title;
    private String contents;
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
