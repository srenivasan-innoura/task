package com.example.task.controller;

import com.example.task.pojo.Customers;
import com.example.task.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/customers")
    public Long saveCustomer(@RequestBody Customers customers){
        customerService.createCustomer(customers);
        return customers.getCustomer_id();
    }

    @GetMapping("/customers")
    public List<Customers> getAllCustomers(){
        return customerService.retrieveAllCustomers();
    }

    @GetMapping("/customers/{customer_id}")
    public Customers getCustomerById(@PathVariable("customer_id") int customer_id){
        return customerService.retrieveCustomersById(customer_id);
    }

    @PutMapping("/customers")
    public Customers updateCustomer( @RequestBody Customers customers){
        customerService.updateCustomer(customers);
        return customers;
    }

    @DeleteMapping("/customers/{customer_id}")
    public void deleteCustomer(@PathVariable("customer_id") int customer_id){
        customerService.deleteCustomer(customer_id);
    }
}
