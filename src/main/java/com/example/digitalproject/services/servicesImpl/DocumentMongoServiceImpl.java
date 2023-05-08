package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.models.documents.DocumentDocument;
import com.example.digitalproject.models.entities.Document;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.DocumentMongoRepository;
import com.example.digitalproject.repositories.DocumentRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.DocumentMongoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class DocumentMongoServiceImpl implements DocumentMongoService {
    private DocumentMongoRepository documentMongoRepository;

    private PersonRepository personRepository;

    private DocumentRepository documentRepository;

    @Override
    public DocumentDocument getDocument(String id) {
        return documentMongoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Document not found"));
    }

    @Override
    public void postDocument(byte[] bytes, String fileName, String email, String token) {
        List<Person> personByToken = personRepository.getPersonByToken(token);
        Person person = personByToken.get(0);
        DocumentDocument documentDocument = new DocumentDocument(null, fileName, bytes, email);
        documentDocument = documentMongoRepository.save(documentDocument);

        Document document = new Document(null, "Документ", documentDocument.getId(), person);
        documentRepository.save(document);
    }

    @Override
    public List<DocumentDocument> getAllDocumentsByToken(String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        List<DocumentDocument> result = new ArrayList<>();
        person.getDocuments().forEach(x -> {
            result.add(documentMongoRepository.findById(x.getDocumentMongoId()).orElseThrow(
                    () -> new ResponseStatusException(NOT_FOUND, "Document not found")));
        });
        return result;
    }

    @Override
    public void deleteDocument(String id) {
        documentMongoRepository.deleteById(id);
    }

    @Override
    public void putDocument(byte[] bytes, String id, String fileName, String email) {
        DocumentDocument documentDocument = new DocumentDocument(id, fileName, bytes, email);
        documentMongoRepository.save(documentDocument);
    }

    @Override
    public List<DocumentDocument> getAllDocuments() {
        return documentMongoRepository.findAll();
    }
}
