package com.example.task.repository;

import com.example.task.pojo.AccountSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsSequenceRepository extends MongoRepository<AccountSequence, String> {
}
