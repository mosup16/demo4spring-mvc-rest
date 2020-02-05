package com.mo16.demo4springmvcrest.api.v1.maodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorDTO {
    private Long id;
    private String name;
    private String vendor_URL;
}
