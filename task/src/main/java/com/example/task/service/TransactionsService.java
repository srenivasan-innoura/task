package com.example.task.service;

import com.example.task.pojo.Accounts;
import com.example.task.pojo.TransactionRequest;
import com.example.task.pojo.TransactionSequence;
import com.example.task.pojo.Transactions;
import com.example.task.repository.AccountsRepository;
import com.example.task.repository.TransactionSequenceRepository;
import com.example.task.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    TransactionSequenceRepository transactionSequenceRepository;
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountsRepository accountsRepository;

    public void createTransaction(Transactions transactions){
        transactions.setTransaction_id(generateSequence(Transactions.name));
        transactionsRepository.save(transactions);
    }
    public Long generateSequence(String seqName) {
        TransactionSequence counter = transactionSequenceRepository.findById(seqName).orElse(new TransactionSequence());
        if (counter.getSeq() == null) {
            counter.setId(seqName);
            counter.setSeq(1L);
        } else {
            counter.setSeq(counter.getSeq() + 1);
        }
        return transactionSequenceRepository.save(counter).getSeq();
    }

    public List<Transactions> retrieveAllTransactions(){
        List<Transactions> transactions = new ArrayList<>();
        transactionsRepository.findAll().forEach(transactions1 -> transactions.add(transactions1));
        return transactions;
    }

    public Transactions retrieveTransactionsById(int id){
         return transactionsRepository.findById((long) id).get();
    }

    public Transactions updateTransactions(Transactions transactions){
        Transactions existingTransaction = transactionsRepository.findById(transactions.getTransaction_id()).get();
        existingTransaction.setTransaction_type(transactions.getTransaction_type());
        existingTransaction.setAmount(transactions.getAmount());
        Transactions updatedTransactions = transactionsRepository.save(existingTransaction);
        return updatedTransactions;
    }

    public void deleteTransactions(int id){
        transactionsRepository.deleteById((long) id);
    }

    public String doTransactions(TransactionRequest request)
    {
        Accounts accounts= accountsRepository.findById(request.getId()).get();
        if(request.getType().equals("DEBIT") && accounts.getBalance()>request.getAmount() )
        {
        double debitedAmount= accounts.getBalance()-request.getAmount();
        accounts.setBalance(debitedAmount);
        Transactions transactions = new Transactions();
        transactions.setTransaction_type(request.getType());
        transactions.setAmount(request.getAmount());
        transactions.setAccount_id(request.getId().intValue());
        accountsRepository.save(accounts);
            return "Your current balance "+debitedAmount;
        }
        else if(request.getType().equals("CREDIT"))
        {
            double creditedAmount= accounts.getBalance()+request.getAmount();
            accounts.setBalance(creditedAmount);
            Transactions transactions = new Transactions();
            transactions.setTransaction_type(request.getType());
            transactions.setAmount(request.getAmount());
            transactions.setAccount_id(request.getId().intValue());
            accountsRepository.save(accounts);
            return "Your current balance "+creditedAmount;
        }
        else {
            return "Enter the right option";
        }

    }


}



