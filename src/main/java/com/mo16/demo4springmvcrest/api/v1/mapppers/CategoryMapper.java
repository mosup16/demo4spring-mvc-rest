package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;
import com.mo16.demo4springmvcrest.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryDtoFromCategory(Category category);
    Category categoryFromCategoryDTo(CategoryDTO categoryDTO);
}
