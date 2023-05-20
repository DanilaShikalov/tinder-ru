package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.models.documents.DocumentDocument;
import com.example.digitalproject.models.entities.*;
import com.example.digitalproject.repositories.*;
import com.example.digitalproject.services.AnswerMongoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class AnswerMongoServiceImpl implements AnswerMongoService {
    private AnswerMongoRepository answerMongoRepository;
    private AnswerRepository answerRepository;
    private PersonRepository personRepository;
    private GradeRepository gradeRepository;
    private SubjectRepository subjectRepository;

    @Override
    public void createAnswerByToken(byte[] bytes, String fileName, String token, String subject, String task) {
        Person person = personRepository.getPersonByToken(token).get(0);
        AnswerDocument answerDocument = new AnswerDocument(null, fileName, bytes, person.getUser().getEmail());
        answerDocument = answerMongoRepository.save(answerDocument);
        Task taskEntity = subjectRepository.findFirstByName(subject).getTasks().stream().filter(x -> x.getTask().equals(task)).findFirst().get();
        Answer answer = new Answer(null, LocalDate.now(), answerDocument.getId(), person, null, taskEntity);
        answer = answerRepository.save(answer);
        gradeRepository.save(new Grade(null, "Нет", "Нет", answer));
    }

    @Override
    public AnswerDocument getDocument(String id) {
        return answerMongoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Document not found"));
    }

    @Override
    public void postDocument(byte[] bytes, String fileName, String email) {
        AnswerDocument answerDocument = new AnswerDocument(null, fileName, bytes, email);
        answerMongoRepository.save(answerDocument);
    }

    @Override
    public void deleteDocument(String id) {
        answerMongoRepository.deleteById(id);
    }

    @Override
    public void putDocument(byte[] bytes, String id, String fileName, String email) {
        AnswerDocument answerDocument = new AnswerDocument(id, fileName, bytes, email);
        answerMongoRepository.save(answerDocument);
    }

    @Override
    public List<AnswerDocument> getAllDocuments() {
        return answerMongoRepository.findAll();
    }
}
