package com.shopsru.retailapp.service;

import com.shopsru.retailapp.dtos.CustomerUpdateDto;
import com.shopsru.retailapp.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getCustomerByName(String name);
    Customer updateCustomer(Long id, CustomerUpdateDto customer);
    Customer deleteCustomer(Long id);
}
