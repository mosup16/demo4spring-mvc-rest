package com.mo16.demo4springmvcrest.controllers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;
import com.mo16.demo4springmvcrest.services.CategoryService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    MockMvc mockMvc;
    @InjectMocks
    CategoryController categoryController;
    private List<CategoryDTO> categories;

    private Long id = 1L;
    private String name = "fruits";
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryDTO = new CategoryDTO(id, name);
        categories = Collections.singletonList(categoryDTO);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @SneakyThrows
    @Test
    void categoryList() {
        when(categoryService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(1)));
    }

    @SneakyThrows
    @Test
    void getCategoryByName() {
        when(categoryService.getCategoryByName(any())).thenReturn(categoryDTO);
        mockMvc.perform(MockMvcRequestBuilders.get(CategoryController.BASE_URL + "/" + name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)));
    }
}