package com.example.digitalproject.repositories;

import com.example.digitalproject.models.documents.AnswerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerMongoRepository extends MongoRepository<AnswerDocument, String> {
}
