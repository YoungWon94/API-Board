package com.won.board.controller.vo.post;

import com.won.board.entity.Category;
import com.won.board.entity.Member;
import com.won.board.entity.Post;
import com.won.board.entity.RoleType;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 게시글 목록 조회 결과
 */
@ApiModel("카테고리 게시글 조회 결과")
@Getter
@Setter(AccessLevel.PRIVATE)
public class FindPostByCategoryResult {

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

        private long postNo; //게시글 번호
        private boolean isNotice;
        private String title;
        private WriterMemberDto writerMember;

        public PostDto(@NonNull Post post) {
            this.postNo = post.getPostNo();
            this.isNotice = post.getIsNotice();
            this.title = post.getTitle();
            this.writerMember = new WriterMemberDto(post.getMember());
        }
    }

    @ApiModel("카테고리게시글>작성자정보")
    @Getter
    public static class WriterMemberDto {

        private Long memberNo;
        private String id; //회원Id
        private String name; //이름
        private String roleType;

        public WriterMemberDto(@NonNull Member member) {
            this.memberNo = member.getMemberNo();
            this.id = member.getId();
            this.name = member.getName();
            this.roleType = member.getRoleType().name();
        }
    }


}
