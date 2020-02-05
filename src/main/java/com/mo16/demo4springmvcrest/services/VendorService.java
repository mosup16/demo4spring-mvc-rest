package com.mo16.demo4springmvcrest.services;

import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
