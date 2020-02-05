package com.mo16.demo4springmvcrest.controllers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerDTO;
import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerListDTO;
import com.mo16.demo4springmvcrest.api.v1.mapppers.CustomerMapper;
import com.mo16.demo4springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO putCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){
        return customerService.updateCustomer(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){
        return customerService.updateCustomer(id, customerDTO);
    }
}
