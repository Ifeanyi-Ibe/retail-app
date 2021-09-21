package com.shopsru.retailapp.controllers;

import com.shopsru.retailapp.Response.ResponseDto;
import com.shopsru.retailapp.dtos.CustomerUpdateDto;
import com.shopsru.retailapp.model.Customer;
import com.shopsru.retailapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if(customers == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No customer found."));
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getCustomerByName(@RequestParam String name) {
        List<Customer> customers = customerService.getCustomerByName(name);
        if(customers.size() > 0)
            return ResponseEntity.ok(customers);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No customer found for name: " + name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer != null)
            return ResponseEntity.ok(customer);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(HttpStatus.NOT_FOUND.value(), "No customer found for id: " + id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer response = customerService.createCustomer(customer);
        if(response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Customer already exists."));
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable("id") Long id, @RequestBody CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerService.updateCustomer(id, customerUpdateDto);

        if(customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Invalid id. Customer does not exist."));
        }

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.deleteCustomer(id);
        if(customer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Customer does not exist. Nothing to delete."));
        return ResponseEntity.ok("Successfully erased customer record.");
    }
}
