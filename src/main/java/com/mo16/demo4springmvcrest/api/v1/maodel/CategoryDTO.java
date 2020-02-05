package com.mo16.demo4springmvcrest.api.v1.maodel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
}
