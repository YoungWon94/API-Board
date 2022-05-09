package com.won.board.facade;

import com.won.board.controller.vo.category.CreateCategoryParam;
import com.won.board.entity.Category;
import com.won.board.entity.Member;
import com.won.board.entity.RoleType;
import com.won.board.exception.CommonException;
import com.won.board.exception.NotFoundException;
import com.won.board.exception.NotFoundMemberException;
import com.won.board.exception.PermissionDeniedException;
import com.won.board.repository.CategoryRepository;
import com.won.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;


    /**
     * 카테고리 생성
     * @param param 생성 파라미터
     */
    public void createCategory(CreateCategoryParam param) throws CommonException {

        /* 관리자 권한 체크 */
        Member member = memberRepository.findByMemberId(param.getMemberId())
                .orElseThrow(NotFoundMemberException::new);
        if(!RoleType.ADMIN.equals(member.getRoleType())) {
            throw new PermissionDeniedException();
        }

        /* 카테고리 생성 */
        Category category = Category.of(param.getName(), member);
        categoryRepository.save(category);
    }
}
