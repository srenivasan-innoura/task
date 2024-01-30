package com.example.task.repository;

import com.example.task.pojo.TransactionSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionSequenceRepository extends MongoRepository<TransactionSequence,String> {
}
