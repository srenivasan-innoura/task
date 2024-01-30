package com.example.task.controller;

import com.example.task.pojo.Accounts;
import com.example.task.pojo.Customers;
import com.example.task.repository.CustomerRepository;
import com.example.task.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {
    @Autowired
    AccountsService accountsService;
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/accounts")
    public void createAccount(@RequestBody Accounts accounts){
        accountsService.createAccount(accounts);
    }

    @GetMapping("/accounts")
    public List<Accounts> retrieveAllAccounts(){
        return accountsService.retrieveAllAccounts();
    }

    @GetMapping("/accounts/{account_id}")
    public Accounts retrieveCustomerById(@PathVariable("account_id") int account_id){
        return accountsService.retrieveAccountsById(account_id);
    }

    @PutMapping("/accounts")
    public Accounts updateAccount( @RequestBody Accounts accounts){
        accountsService.updateAccounts(accounts);
        return accounts;
    }

    @DeleteMapping("/accounts/{account_id}")
    public void deleteCustomer(@PathVariable("account_id") int account_id){
        accountsService.deleteAccount(account_id);
    }
}
