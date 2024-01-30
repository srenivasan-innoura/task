package com.example.task.service;

import com.example.task.pojo.AccountSequence;
import com.example.task.pojo.Accounts;
import com.example.task.pojo.CustomerSequence;
import com.example.task.pojo.Customers;
import com.example.task.repository.AccountsRepository;
import com.example.task.repository.AccountsSequenceRepository;
import com.example.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {
    @Autowired
    AccountsSequenceRepository accountsSequenceRepository;
    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    CustomerRepository customerRepository;

    public void createAccount(Accounts accounts) {
        accounts.setAccount_id(generateSequence(Accounts.name));
        accountsRepository.save(accounts);
    }
    public Long generateSequence(String seqName) {
        AccountSequence count = accountsSequenceRepository.findById(seqName).orElse(new AccountSequence());
        if (count.getSeq() == null) {
//            customerSequence = new CustomerSequence();
            count.setId(seqName);
            count.setSeq(1L);
        } else {
            count.setSeq(count.getSeq() + 1);
        }
        count =  accountsSequenceRepository.save(count);
        return count.getSeq();
    }

    public List<Accounts> retrieveAllAccounts() {
        List<Accounts> accounts = new ArrayList<>();
        accountsRepository.findAll().forEach(accounts1 -> accounts.add(accounts1));
        return accounts;
    }

    public Accounts retrieveAccountsById(int id) {
        return accountsRepository.findById((long)id).get();
    }

    public Accounts updateAccounts(Accounts accounts) {
        Accounts existingAccount = accountsRepository.findById(accounts.getAccount_id()).get();
        existingAccount.setAccount_type(accounts.getAccount_type());
        existingAccount.setBalance(accounts.getBalance());
        Accounts updatedAccount = accountsRepository.save(existingAccount);
        return updatedAccount;
    }

    public void deleteAccount(int id) {
        accountsRepository.deleteById((long) id);
    }
}
