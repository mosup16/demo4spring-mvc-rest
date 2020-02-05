package com.mo16.demo4springmvcrest.services;

import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.api.v1.mapppers.VendorMapper;
import com.mo16.demo4springmvcrest.controllers.VendorController;
import com.mo16.demo4springmvcrest.domain.Vendor;
import com.mo16.demo4springmvcrest.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper mapper;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper mapper) {
        this.vendorRepository = vendorRepository;
        this.mapper = mapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDto = mapper.vendorDtoFromVendor(vendor);
                    vendorDto.setVendor_URL(getVendor_url(vendor.getId()));
                    return vendorDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        VendorDTO vendorDTO = mapper.vendorDtoFromVendor(vendorRepository.findById(id).orElseThrow(RuntimeException::new));
        vendorDTO.setVendor_URL(getVendor_url(id));
        return vendorDTO; //todo implement exception handling
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(mapper.vendorFromVendorDto(vendorDTO));
        Long id = savedVendor.getId();
        vendorDTO.setId(id);
        vendorDTO.setVendor_URL(getVendor_url(id));
        return vendorDTO;
    }


    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        vendorRepository.save(mapper.vendorFromVendorDto(vendorDTO));
        vendorDTO.setVendor_URL(getVendor_url(id));
        return vendorDTO;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendor_url(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
