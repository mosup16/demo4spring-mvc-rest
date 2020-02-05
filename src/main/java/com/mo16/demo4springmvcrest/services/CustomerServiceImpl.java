package com.mo16.demo4springmvcrest.services;

import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerDTO;
import com.mo16.demo4springmvcrest.api.v1.mapppers.CustomerMapper;
import com.mo16.demo4springmvcrest.controllers.CustomerController;
import com.mo16.demo4springmvcrest.domain.Customer;
import com.mo16.demo4springmvcrest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = mapper.CustomerDtoTFromCustomer(customer);
                    customerDTO.setCustomer_URL(getCustomer_url(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList())
                ;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerDTO customerDTO = mapper.CustomerDtoTFromCustomer(customerRepository.findById(id).orElseThrow(RuntimeException::new));
        customerDTO.setCustomer_URL(getCustomer_url(customerDTO.getId()));
        return customerDTO;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return saveAndReturnCustomerDTO(customerDTO);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return saveAndReturnCustomerDTO(customerDTO);
    }

    private CustomerDTO saveAndReturnCustomerDTO(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(mapper.CustomerFromCustomerDto(customerDTO));
        customerDTO.setId(savedCustomer.getId());
        customerDTO.setCustomer_URL(getCustomer_url(savedCustomer.getId()));
        return customerDTO;
    }

    private String getCustomer_url(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

}
