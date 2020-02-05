package com.mo16.demo4springmvcrest.services;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;
import com.mo16.demo4springmvcrest.api.v1.mapppers.CategoryMapper;
import com.mo16.demo4springmvcrest.domain.Category;
import com.mo16.demo4springmvcrest.repositories.CategoryRepository;
import com.mo16.demo4springmvcrest.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper mapper;

    List<Category> categories;
    Category category;
    CategoryServiceImpl service;

    Long id = 1L;
    String name = "fruits";

    @BeforeEach
    void setUp() {
        categories = Arrays.asList(new Category(id, name));
        service = new CategoryServiceImpl(categoryRepository, mapper);
        category = new Category(id, name);
    }

    @Test
    void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(categories);
        when(mapper.categoryDtoFromCategory(any())).thenReturn(new CategoryDTO(id, name));

        List<CategoryDTO> allCategories = service.getAllCategories();
        assert allCategories.size() == 1;
        allCategories.forEach(categoryDTO -> {
            assert id.equals(categoryDTO.getId());
            assert name.equals(categoryDTO.getName());
        });

        verify(categoryRepository, times(1)).findAll();
        verify(mapper, times(1)).categoryDtoFromCategory(any());

    }

    @Test
    void getCategoryByName() {
        when(categoryRepository.findByName(any())).thenReturn(category);
        when(mapper.categoryDtoFromCategory(any())).thenReturn(new CategoryDTO(id, name));

        CategoryDTO categoryDTO = service.getCategoryByName(any());
        assert id.equals(categoryDTO.getId());
        assert name.equals(categoryDTO.getName());

        verify(categoryRepository, times(1)).findByName(any());
        verify(mapper, times(1)).categoryDtoFromCategory(any());

    }
}