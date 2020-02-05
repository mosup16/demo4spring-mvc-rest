package com.mo16.demo4springmvcrest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo16.demo4springmvcrest.api.v1.maodel.VendorDTO;
import com.mo16.demo4springmvcrest.domain.Vendor;
import com.mo16.demo4springmvcrest.services.VendorService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    private MockMvc mockMvc;
    private long id;
    private List<VendorDTO> vendors;
    private VendorDTO vendorDto;
    private String name;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
        id = 1L;
        name = "mo";
        vendor = new Vendor(id, name);
        vendorDto = new VendorDTO(id, name, VendorController.BASE_URL + "/" + id);
        vendors = Arrays.asList(vendorDto);
    }

    @SneakyThrows
    @Test
    void getAllVendors() {
        when(vendorService.getAllVendors()).thenReturn(vendors);
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(1)))
        ;
        verify(vendorService, times(1)).getAllVendors();
    }

    @SneakyThrows
    @Test
    void getVendorById() {
        when(vendorService.getVendorById(any())).thenReturn(vendorDto);
        mockMvc.perform(get(VendorController.BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
        ;
        verify(vendorService, times(1)).getVendorById(any());
    }

    @SneakyThrows
    @Test
    void createVendor() {
        when(vendorService.createVendor(any())).thenReturn(vendorDto);
        mockMvc.perform(post(VendorController.BASE_URL)
                .content(getVendorAsJson(vendorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
        ;
        verify(vendorService, times(1)).createVendor(any());
    }

    @SneakyThrows
    @Test
    void updateVendor() {
        when(vendorService.updateVendor(any(), any())).thenReturn(vendorDto);
        mockMvc.perform(put(VendorController.BASE_URL + "/" + id)
                .content(getVendorAsJson(vendorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
        ;
        verify(vendorService, times(1)).updateVendor(any(), any());
    }

    @SneakyThrows
    @Test
    void patchVendor() {
        when(vendorService.updateVendor(any(), any())).thenReturn(vendorDto);
        mockMvc.perform(patch(VendorController.BASE_URL + "/" + id)
                .content(getVendorAsJson(vendorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
        ;
        verify(vendorService, times(1)).updateVendor(any(), any());
    }

    private String getVendorAsJson(VendorDTO vendorDTO) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(vendor);
    }
}