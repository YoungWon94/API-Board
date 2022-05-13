package com.won.board.facade;


import com.won.board.controller.dto.post.CreatePostParam;
import com.won.board.controller.dto.post.FindPostByCategoryResult;
import com.won.board.controller.dto.post.FindPostResult;
import com.won.board.entity.Category;
import com.won.board.entity.Member;
import com.won.board.entity.Post;
import com.won.board.exception.NotFoundException;
import com.won.board.repository.CategoryRepository;
import com.won.board.repository.MemberRepository;
import com.won.board.repository.PostRepository;
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
}
