package com.example.digitalproject.services;

import com.example.digitalproject.models.documents.AnswerDocument;

import java.util.List;

public interface AnswerMongoService {
    AnswerDocument getDocument(String id);

    void postDocument(byte[] bytes, String fileName);

    void deleteDocument(String id);

    void putDocument(byte[] bytes, String id, String fileName);

    List<AnswerDocument> getAllDocuments();
}
