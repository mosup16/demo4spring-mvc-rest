package com.mo16.demo4springmvcrest.api.v1.maodel;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryListDTO {
    private List<CategoryDTO> categories;
}
