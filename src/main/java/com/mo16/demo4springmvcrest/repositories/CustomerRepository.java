package com.mo16.demo4springmvcrest.repositories;

import com.mo16.demo4springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
