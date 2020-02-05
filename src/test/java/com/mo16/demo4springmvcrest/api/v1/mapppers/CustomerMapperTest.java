package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerDTO;
import com.mo16.demo4springmvcrest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


class CustomerMapperTest {

    private String lastName;
    private String firstName;
    private Long id;
    private Customer customer;
    private CustomerDTO customerDto;
    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        lastName = "em";
        firstName = "mo";
        id = 1L;
        customer = new Customer(id, firstName, lastName);
        customerDto = new CustomerDTO(id, firstName, lastName, "url");
        mapper = Mappers.getMapper(CustomerMapper.class);
    }

    @Test
    void customerDtoTFromCustomer() {
        CustomerDTO mappedCustomerDTO = mapper.CustomerDtoTFromCustomer(customer);
        assert id.equals(mappedCustomerDTO.getId());
        assert firstName.equals(mappedCustomerDTO.getFirstName());
        assert lastName.equals(mappedCustomerDTO.getLastName());
    }

    @Test
    void customerFromCustomerDto() {
        Customer mappedCustomer = mapper.CustomerFromCustomerDto(customerDto);
        assert id.equals(mappedCustomer.getId());
        assert firstName.equals(mappedCustomer.getFirstName());
        assert lastName.equals(mappedCustomer.getLastName());
    }
}