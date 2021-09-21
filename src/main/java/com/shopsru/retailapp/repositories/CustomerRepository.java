package com.shopsru.retailapp.repositories;

import com.shopsru.retailapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerByName(String name);
    List<Customer> findCustomerByEmail(String email);
    List<Customer> findCustomerByPhoneNumber(String number);
}
