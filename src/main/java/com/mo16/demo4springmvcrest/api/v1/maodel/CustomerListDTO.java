package com.mo16.demo4springmvcrest.api.v1.maodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
