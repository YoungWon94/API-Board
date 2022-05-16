package com.won.board.facade;


import com.won.board.controller.dto.comment.CreateCommentParam;
import com.won.board.controller.dto.comment.DeleteCommentParam;
import com.won.board.controller.dto.comment.ModifyCommentParam;
import com.won.board.entity.Comment;
import com.won.board.entity.Member;
import com.won.board.entity.Post;
import com.won.board.exception.CommonException;
import com.won.board.exception.NotFoundException;
import com.won.board.exception.PermissionDeniedException;
import com.won.board.repository.CommentRepository;
import com.won.board.repository.MemberRepository;
import com.won.board.repository.PostRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CommentFacade {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 생성
     *
     * @param param 생성 파라미터
     */
    public void createComment(@NonNull CreateCommentParam param) throws CommonException {

        /* 회원 조회 및 체크 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));

        /* 게시글 조회 및 체크 */
        Post post = postRepository.findById(param.getPostNo())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 게시글입니다."));

        /* 상위 댓글 조회 및 체크 */
        Comment parentComment = param.getParentCommentNo() == null
                ? null
                : commentRepository.findByCommentNo(param.getParentCommentNo())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 상위댓글 입니다."));

        /* 댓글 생성 */
        Comment comment = Comment.of(parentComment, post, member, param.getContents());
        commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     *
     * @param commentNo 수정 댓글 번호
     * @param param 수정 파라미터
     */
    public void modifyComment(long commentNo, @NonNull ModifyCommentParam param) throws CommonException {

        /* 회원 조회 및 체크 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));

        /* 댓글 조회 및 체크 */
        Comment comment = commentRepository.findByCommentNo(commentNo)
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 댓글 입니다."));

        if(!comment.getMember().equals(member)) {
            throw new PermissionDeniedException();
        }

        /* 댓글 내용 수정 */
        comment.changeContents(param.getContents());

    }

    /**
     * 댓글 삭제
     *
     * @param commentNo 댓글번호
     * @param param 삭제 파라미터
     */
    public void deleteComment(long commentNo, @NonNull DeleteCommentParam param) throws CommonException {

        /* 회원 조회 및 체크 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));

        /* 댓글 조회 및 체크 */
        Comment comment = commentRepository.findByCommentNo(commentNo)
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 댓글 입니다."));

        if(!comment.getMember().equals(member)) {
            throw new PermissionDeniedException();
        }

        /* 댓글 삭제 */
        commentRepository.deleteByCommentNo(commentNo);

    }
}
