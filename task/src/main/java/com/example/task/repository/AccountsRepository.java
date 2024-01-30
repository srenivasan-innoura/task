package com.example.task.repository;

import com.example.task.pojo.Accounts;
import com.example.task.pojo.Transactions;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends MongoRepository<Accounts, Long> {
}
