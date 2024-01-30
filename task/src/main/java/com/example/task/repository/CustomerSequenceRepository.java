package com.example.task.repository;

import com.example.task.pojo.CustomerSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerSequenceRepository extends MongoRepository<CustomerSequence , String> {
}
