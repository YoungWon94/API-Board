package com.won.board.facade;


import com.won.board.controller.dto.post.CreatePostParam;
import com.won.board.controller.dto.post.FindPostByCategoryResult;
import com.won.board.controller.dto.post.FindPostResult;
import com.won.board.controller.dto.post.ModifyPostParam;
import com.won.board.entity.Category;
import com.won.board.entity.Member;
import com.won.board.entity.Post;
import com.won.board.exception.CommonException;
import com.won.board.exception.NotFoundException;
import com.won.board.exception.PermissionDeniedException;
import com.won.board.repository.CategoryRepository;
import com.won.board.repository.MemberRepository;
import com.won.board.repository.PostRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 게시글 생성
     */
    public void createPost(@NotNull CreatePostParam param) throws NotFoundException {

       /* 회원 조회 및 체크 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));

        /* 카테고리 조회 및 체크 */
        Category category = categoryRepository.findById(param.getCategoryNo())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 카테고리입니다."));

        /* 게시글 생성 */
        Post post = Post.of(category, member, false, param.getTitle(), param.getContents());
        postRepository.save(post);

    }

    /**
     * 카테고리의 게시글 목록 조회
     */
    public FindPostByCategoryResult findPostByCategory(long categoryNo) throws NotFoundException {

        /* 카테고리 조회 및 체크 */
        Category category = categoryRepository.findById(categoryNo)
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 카테고리입니다."));

        /* 게시글 조회 */
        List<Post> postList = postRepository.findByCategoryNo(categoryNo);

        /* 결과 반환 */
        List<FindPostByCategoryResult.PostDto> postDtoList = postList.stream()
                .map(FindPostByCategoryResult.PostDto::new)
                .collect(Collectors.toList());

        return FindPostByCategoryResult.from(postDtoList);
   }

    /**
     * 게시글 단건 조회
     *
     * @param postNo 게시글번호
     */
    public FindPostResult findPost(long postNo) throws NotFoundException {

        /* 게시글 조회 */
        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 게시글입니다."));

        /* 결과 반환 */
        return FindPostResult.from(post);
    }

    /**
     * 게시글 수정
     *
     * @param postNo 게시글번호
     * @param param 수정 파라미터
     */
    public void modifyPost(long postNo, @NonNull ModifyPostParam param) throws CommonException {

        /* 게시글 조회 */
        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 게시글입니다."));

        /* 회원정보 조회 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));

        /* 작성자 체크 */
        if(!member.equals(post.getMember())) {
            throw new PermissionDeniedException();
        }

        /* 게시글 수정 */

        //변경 카테고리 조회 및 수정
        long newCategoryNo = param.getCategoryNo();
        if(post.getCategory().getCategoryNo() != newCategoryNo) {
            Category newCategory = categoryRepository.findById(newCategoryNo)
                    .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 카테고리입니다."));
            post.setCategory(newCategory);
        }
        //제목,내용 수정
        post.changetitle(param.getTitle());
        post.changeContents(param.getContents());

    }
}
