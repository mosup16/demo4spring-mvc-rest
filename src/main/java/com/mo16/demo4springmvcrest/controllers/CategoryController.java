package com.mo16.demo4springmvcrest.controllers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;
import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryListDTO;
import com.mo16.demo4springmvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO categoryList() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
