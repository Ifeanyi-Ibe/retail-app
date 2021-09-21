package com.shopsru.retailapp.serviceImpl;

import com.shopsru.retailapp.dtos.CustomerUpdateDto;
import com.shopsru.retailapp.model.Customer;
import com.shopsru.retailapp.repositories.CustomerRepository;
import com.shopsru.retailapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if(customers.size() < 1) return null;
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        List<Customer> customersByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        List<Customer> customersByPhone = customerRepository.findCustomerByPhoneNumber(customer.getPhoneNumber());

        if(customersByEmail.size() > 0 || customersByPhone.size() > 0) return null;

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(!customerOptional.isPresent()) {
            return null;
        }

        Customer customer = customerOptional.get();

        if(customerUpdateDto.getName() != null) {
            customer.setName(customerUpdateDto.getName());
        }
        if(customerUpdateDto.getEmail() != null) {
            customer.setEmail(customerUpdateDto.getEmail());
        }
        if(customerUpdateDto.getPhoneNumber() != null) {
            customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        }
        if(customerUpdateDto.isAffiliate() && !customer.isAffiliate()) {
            customer.setAffiliate(true);
        } else if(!customerUpdateDto.isAffiliate() && customer.isAffiliate()) {
            customer.setAffiliate(false);
        }
        if(customerUpdateDto.isEmployee() && !customer.isEmployee()) {
            customer.setEmployee(true);
        } else if(!customerUpdateDto.isEmployee() && customer.isEmployee()) {
            customer.setEmployee(false);
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            customerRepository.deleteById(id);
            return customer.get();
        }
        return null;
    }
}
