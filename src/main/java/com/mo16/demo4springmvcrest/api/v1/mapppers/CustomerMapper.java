package com.mo16.demo4springmvcrest.api.v1.mapppers;

import com.mo16.demo4springmvcrest.api.v1.maodel.CustomerDTO;
import com.mo16.demo4springmvcrest.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO CustomerDtoTFromCustomer(Customer customer);
    Customer CustomerFromCustomerDto(CustomerDTO customerDTO);
}
