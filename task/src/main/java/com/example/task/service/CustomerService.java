package com.example.task.service;

import com.example.task.pojo.CustomerSequence;
import com.example.task.pojo.Customers;
import com.example.task.repository.CustomerRepository;
import com.example.task.repository.CustomerSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerSequenceRepository customerSequenceRepository;
    public void createCustomer(Customers customers){
        customers.setCustomer_id(generateSequence(Customers.name));
        customerRepository.save(customers);
    }

    public Long generateSequence(String seqName) {
        CustomerSequence counter = customerSequenceRepository.findById(seqName).orElse(new CustomerSequence());
        if (counter.getSeq() == null) {
//            customerSequence = new CustomerSequence();
            counter.setId(seqName);
            counter.setSeq(1L);
        } else {
            counter.setSeq(counter.getSeq() + 1);
        }
    return customerSequenceRepository.save(counter).getSeq();
    }
    public List<Customers> retrieveAllCustomers(){
        List<Customers> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers1 -> customers.add(customers1));
        return customers;
    }

    public Customers retrieveCustomersById(int id){
        return customerRepository.findById((long) id).get();
    }

    public Customers updateCustomer(Customers customers){
        Customers existingCustomer = customerRepository.findById(customers.getCustomer_id()).get();
        existingCustomer.setFirst_name(customers.getFirst_name());
        existingCustomer.setLast_name(customers.getLast_name());
        existingCustomer.setEmail(customers.getEmail());
        existingCustomer.setAddress(customers.getAddress());
        existingCustomer.setPhone_number(customers.getPhone_number());
        Customers updatedCustomer = customerRepository.save(existingCustomer);
        return updatedCustomer;
    }

    public void deleteCustomer(int id){
        customerRepository.deleteById((long)id);
    }


}
