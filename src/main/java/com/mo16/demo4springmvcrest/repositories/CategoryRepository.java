package com.mo16.demo4springmvcrest.repositories;

import com.mo16.demo4springmvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category ,Long> {
    Category findByName(String name);
}
