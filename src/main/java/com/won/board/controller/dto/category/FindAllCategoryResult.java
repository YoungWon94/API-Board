package com.won.board.controller.dto.category;

import com.won.board.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * 전체 카테고리 조회 결과
 */
@ApiModel("전체 카테고리 조회 결과")
@Getter @Setter(AccessLevel.PRIVATE)
public class FindAllCategoryResult {

    @ApiModelProperty(required = true, name = "카테고리 목록", position = 10)
    private List<CategoryDto> categoryList;

    //===== 생성 메서드 =====//

    public static FindAllCategoryResult from(@NonNull List<CategoryDto> categoryDtoList) {
        FindAllCategoryResult result = new FindAllCategoryResult();
        result.setCategoryList(categoryDtoList);
        return result;
    }


    //===== 내부 클래스 =====//

    /**
     * 카테고리 정보 Dto
     */
    @ApiModel("카테고리 정보")
    @Getter
    public static class CategoryDto {

        private long categoryNo;
        private String name;

        public CategoryDto(@NonNull Category category) {
            this.categoryNo = category.getCategoryNo();
            this.name = category.getName();
        }
    }

}
