package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.repositories.AnswerMongoRepository;
import com.example.digitalproject.services.AnswerMongoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class AnswerMongoServiceImpl implements AnswerMongoService {
    private AnswerMongoRepository answerMongoRepository;

    @Override
    public AnswerDocument getDocument(String id) {
        return answerMongoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Document not found"));
    }

    @Override
    public void postDocument(byte[] bytes) {
        AnswerDocument answerDocument = new AnswerDocument(null, bytes);
        answerMongoRepository.save(answerDocument);
    }

    @Override
    public void deleteDocument(String id) {
        answerMongoRepository.deleteById(id);
    }

    @Override
    public void putDocument(byte[] bytes, String id) {
        AnswerDocument answerDocument = new AnswerDocument(id, bytes);
        answerMongoRepository.save(answerDocument);
    }

    @Override
    public List<AnswerDocument> getAllDocuments() {
        return answerMongoRepository.findAll();
    }
}
