package com.won.board.controller.dto.post;

import com.won.board.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 게시글 목록 조회 결과
 */
@ApiModel("카테고리 게시글 조회 결과")
@Getter @Setter(AccessLevel.PRIVATE)
public class FindPostByCategoryResult {

    @ApiModelProperty(required = true, value = "게시글 목록", position = 10)
    private List<PostDto> postList;


    //===== 생성 메서드 =====//

    public static FindPostByCategoryResult from(@NonNull List<PostDto> postList) {
        FindPostByCategoryResult result = new FindPostByCategoryResult();
        result.setPostList(postList);
        return result;
    }

    public static FindPostByCategoryResult emptySet() {
        return from(new ArrayList<>());
    }



    //===== 내부 클라스 =====//

    /**
     * 게시글 정보
     */
    @ApiModel("카테고리게시글>게시글정보")
    @Getter
    public static class PostDto {

        @ApiModelProperty(required = true, value = "게시글 번호", position = 10)
        private Long postNo; //게시글 번호

        @ApiModelProperty(required = true, value = "공지글 여부", position = 20)
        private Boolean isNotice;

        @ApiModelProperty(required = true, value = "게시글 제목", position = 30)
        private String title;

        @ApiModelProperty(required = true, value = "게시글 작성자", position = 40)
        private WriterMemberDto writerMember;

        public PostDto(@NonNull Post post) {
            this.postNo = post.getPostNo();
            this.isNotice = post.getIsNotice();
            this.title = post.getTitle();
            this.writerMember = new WriterMemberDto(post.getMember());
        }
    }

}
