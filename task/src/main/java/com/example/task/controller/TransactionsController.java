package com.example.task.controller;

import com.example.task.pojo.TransactionRequest;
import com.example.task.pojo.Transactions;
import com.example.task.repository.AccountsRepository;
import com.example.task.repository.TransactionsRepository;
import com.example.task.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    AccountsRepository accountsRepository;

    @PostMapping("/transactions")
    public void saveTransaction(@RequestBody Transactions transactions){
        transactionsService.createTransaction(transactions);
    }

    @GetMapping("/transactions")
    public List<Transactions> retrieveAllTransactions(){
        return transactionsService.retrieveAllTransactions();
    }

    @GetMapping("/transactions/{transaction_id}")
    public Transactions retrieveTransactionById(@PathVariable("transaction_id") int transaction_id){
        return transactionsService.retrieveTransactionsById(transaction_id);
    }

    @PutMapping("/transactions")
    public Transactions updateTransaction(@RequestBody Transactions transactions){
        transactionsService.updateTransactions(transactions);
        return transactions;
    }

    @DeleteMapping("/transactions/{transaction_id}")
    public void deleteTransaction(@PathVariable("transaction_id") int transaction_id){
        transactionsService.deleteTransactions(transaction_id);
    }
    @PostMapping("/dotransactions")
    public String doTransaction(@RequestBody TransactionRequest request){
       return transactionsService.doTransactions(request);
    }
}
