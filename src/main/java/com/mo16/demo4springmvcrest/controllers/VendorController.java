package com.mo16.demo4springmvcrest.controllers;

import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.api.v1.maodel.VendorListDTO;
import com.mo16.demo4springmvcrest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors(){
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        vendorDTO.setId(null);
        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@RequestBody VendorDTO vendorDTO, @PathVariable Long id){
        return vendorService.updateVendor(id, vendorDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@RequestBody VendorDTO vendorDTO, @PathVariable Long id){
        return vendorService.updateVendor(id, vendorDTO);
    }
}
