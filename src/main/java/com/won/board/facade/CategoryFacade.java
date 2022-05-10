package com.won.board.facade;

import com.won.board.controller.vo.category.CreateCategoryParam;
import com.won.board.controller.vo.category.FindAllCategoryResult;
import com.won.board.entity.Category;
import com.won.board.entity.Member;
import com.won.board.entity.RoleType;
import com.won.board.exception.CommonException;
import com.won.board.exception.NotFoundException;
import com.won.board.exception.PermissionDeniedException;
import com.won.board.repository.CategoryRepository;
import com.won.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new NotFoundException(400, "존재하지 않는 회원입니다."));
        if(!RoleType.ADMIN.equals(member.getRoleType())) {
            throw new PermissionDeniedException();
        }

        /* 카테고리 생성 */
        Category category = Category.of(param.getName(), member);
        categoryRepository.save(category);
    }

    /**
     * 전체 카테고리 조회
     */
    public FindAllCategoryResult findAllCategory() {

        /* 모든 카테고리 조회 */
        List<Category> categoryList = categoryRepository.findAll();

        /* 결과 반환 */
        List<FindAllCategoryResult.CategoryDto> categoryDtoList = categoryList.stream()
                .map(FindAllCategoryResult.CategoryDto::new)
                .collect(Collectors.toList());
        return FindAllCategoryResult.from(categoryDtoList);
    }
}
