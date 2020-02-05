package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO vendorDtoFromVendor(Vendor vendor);
    Vendor vendorFromVendorDto(VendorDTO vendorDTO);
}
