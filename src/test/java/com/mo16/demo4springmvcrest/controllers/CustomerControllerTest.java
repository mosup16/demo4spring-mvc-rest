package com.mo16.demo4springmvcrest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerDTO;
import com.mo16.demo4springmvcrest.api.v1.mapppers.CustomerMapper;
import com.mo16.demo4springmvcrest.domain.Customer;
import com.mo16.demo4springmvcrest.services.CustomerService;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    CustomerController controller;
    MockMvc mockMvc;

    CustomerDTO customerDTO;
    List<CustomerDTO> customers;
    private String url;
    private String lastName;
    private String firstName;
    private long id;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        url = "url";
        lastName = "em";
        firstName = "mo";
        id = 1L;
        customerDTO = new CustomerDTO(id, firstName, lastName, url);
        customerDTO.setCustomer_URL(CustomerController.BASE_URL + "/" + id);
        customers = Arrays.asList(customerDTO);
    }

    @SneakyThrows
    @Test
    void getAllCustomers() {
        when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(1)))
        ;
    }

    @SneakyThrows
    @Test
    void getCustomerById() {
        when(customerService.getCustomerById(any())).thenReturn(customerDTO);
        mockMvc.perform(get(CustomerController.BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)))
        ;
    }

    @SneakyThrows
    @Test
    void createCustomer() {
        when(customerService.createCustomer(any())).thenReturn(customerDTO);
        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCustomerAsJson(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)))
        ;
    }

    @SneakyThrows
    @Test
    void putCustomer() {
        when(customerService.updateCustomer(any(), any())).thenReturn(customerDTO);
        mockMvc.perform(put(CustomerController.BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCustomerAsJson(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
                .andExpect(jsonPath("$.customer_URL", equalTo(CustomerController.BASE_URL + "/" + id)))
        ;
    }

    @SneakyThrows
    @Test
    void patchCustomer() {
        when(customerService.updateCustomer(any(), any())).thenReturn(customerDTO);
        mockMvc.perform(patch(CustomerController.BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCustomerAsJson(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)))
                .andExpect(jsonPath("$.id", equalTo((int) id)))
                .andExpect(jsonPath("$.customer_URL", equalTo(CustomerController.BASE_URL + "/" + id)))
        ;
    }

    private String getCustomerAsJson(CustomerDTO customer) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(customer);
    }

}