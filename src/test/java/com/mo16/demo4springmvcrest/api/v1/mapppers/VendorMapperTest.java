package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    private VendorMapper mapper;
    private String name = "mo";
    private Long id = 1L;
    private String url = "url";
    private VendorDTO vendorDTO;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor(id, name);
        vendorDTO = new VendorDTO(id, name, url);
        mapper = Mappers.getMapper(VendorMapper.class);
    }

    @Test
    void vendorDtoFromVendor() {
        VendorDTO mappedVendorDTO = mapper.vendorDtoFromVendor(vendor);
        assert id.equals(mappedVendorDTO.getId());
        assert name.equals(mappedVendorDTO.getName());
    }

    @Test
    void vendorFromVendorDto() {
        Vendor mappedVendor = mapper.vendorFromVendorDto(vendorDTO);
        assert id.equals(mappedVendor.getId());
        assert name.equals(mappedVendor.getName());
    }
}