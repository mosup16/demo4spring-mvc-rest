package com.mo16.demo4springmvcrest.services;

import com.mo16.demo4springmvcrest.api.v1.maodel.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
