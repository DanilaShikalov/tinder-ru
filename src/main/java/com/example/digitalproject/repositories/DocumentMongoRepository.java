package com.example.digitalproject.repositories;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.models.documents.DocumentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentMongoRepository extends MongoRepository<DocumentDocument, String> {
}
