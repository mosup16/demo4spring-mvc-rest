package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;
import com.mo16.demo4springmvcrest.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


class CategoryMapperTest {

    private String name;
    private Long id;
    private Category category;
    private CategoryDTO categoryDTO;
    private CategoryMapper mapper;

    @BeforeEach
    void setUp() {
        name = "fresh";
        id = 1L;
        category = new Category(id, name);
        categoryDTO = new CategoryDTO(id, name);
        mapper = Mappers.getMapper(CategoryMapper.class);
    }

    @Test
    void categoryDtoFromCategory() {
        CategoryDTO mappedCustomerDTO = mapper.categoryDtoFromCategory(category);
        assert id.equals(mappedCustomerDTO.getId());
        assert name.equals(mappedCustomerDTO.getName());
    }

    @Test
    void categoryFromCategoryDTo() {
        Category mappedCustomer = mapper.categoryFromCategoryDTo(categoryDTO);
        assert id.equals(mappedCustomer.getId());
        assert name.equals(mappedCustomer.getName());
    }
}