package com.example.digitalproject.services;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.models.documents.DocumentDocument;

import java.util.List;

public interface DocumentMongoService {
    DocumentDocument getDocument(String id);

    void postDocument(byte[] bytes, String fileName, String email, String token);

    void deleteDocument(String id);

    void putDocument(byte[] bytes, String id, String fileName, String email);

    List<DocumentDocument> getAllDocuments();
}
