package com.mo16.demo4springmvcrest.bootstarap;

import com.mo16.demo4springmvcrest.domain.Category;
import com.mo16.demo4springmvcrest.domain.Customer;
import com.mo16.demo4springmvcrest.domain.Vendor;
import com.mo16.demo4springmvcrest.repositories.CategoryRepository;
import com.mo16.demo4springmvcrest.repositories.CustomerRepository;
import com.mo16.demo4springmvcrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    @Autowired
    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
    }

    private void loadData() {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor mo = new Vendor();
        mo.setName("mo");

        Vendor dan = new Vendor();
        dan.setName("dan");

        Vendor frank = new Vendor();
        frank.setName("frank");

        Vendor emma = new Vendor();
        emma.setName("emma");

        Vendor john = new Vendor();
        john.setName("john");

        vendorRepository.save(mo);
        vendorRepository.save(dan);
        vendorRepository.save(frank);
        vendorRepository.save(emma);
        vendorRepository.save(john);

        log.info("#Vendors Count : " + vendorRepository.count() + "#");
    }

    private void loadCustomers() {
        Customer mo = new Customer();
        mo.setFirstName("mo");
        mo.setLastName("em");

        Customer mark = new Customer();
        mark.setFirstName("mark");
        mark.setLastName("watson");

        Customer john = new Customer();
        john.setFirstName("john");
        john.setLastName("martin");

        Customer walter = new Customer();
        walter.setFirstName("walter");
        walter.setLastName("smith");

        Customer alan = new Customer();
        alan.setFirstName("alan");
        alan.setLastName("walker");

        customerRepository.save(mo);
        customerRepository.save(mark);
        customerRepository.save(walter);
        customerRepository.save(john);
        customerRepository.save(alan);

        log.info("#Customers Count : " + customerRepository.count() + "#");
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("fruits");

        Category dried = new Category();
        dried.setName("dried");

        Category fresh = new Category();
        fresh.setName("fresh");

        Category exotic = new Category();
        exotic.setName("exotic");

        Category nuts = new Category();
        nuts.setName("nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(dried);
        categoryRepository.save(nuts);

        log.info("#Category Count : " + categoryRepository.count() + "#");
    }
}
