package com.mo16.demo4springmvcrest.api.v1.maodel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String Customer_URL;
}
