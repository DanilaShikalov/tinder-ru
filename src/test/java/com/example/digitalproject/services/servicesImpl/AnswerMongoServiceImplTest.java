package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.models.entities.*;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerMongoServiceImplTest {
    @Mock
    private AnswerMongoRepository answerMongoRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private AnswerMongoServiceImpl answerMongoService;
    @Captor
    private ArgumentCaptor<Grade> argumentCaptorGrade;
    @Captor
    private ArgumentCaptor<AnswerDocument> argumentCaptorAnswerDocument;
    @Captor
    private ArgumentCaptor<String> argumentCaptorStringId;

    @Test
    void createAnswerByToken() {
        byte[] array = new byte[]{12, 127 , 12};
        String fileName = "test";
        String token = "test";
        String subject = "test";
        String task = "test";
        Person person = new Person(1L, "test", "test", "test", "test", null, null, null, null,
                new User(1L, "test", "test", "test", "test", null, null));
        AnswerDocument answerDocument1 = new AnswerDocument(null, fileName, array, person.getUser().getEmail());
        AnswerDocument answerDocument = new AnswerDocument("id1", "ets", array, "test");
        Task task1 = new Task(1L, "test", LocalDate.MIN, LocalDate.MAX, null, null);
        Answer answer1 = new Answer(null, LocalDate.now(), answerDocument.getId(), person, null, task1);
        Answer answer = new Answer(1L, LocalDate.MIN, "tetst", null, null, null);
        Grade grade = new Grade(null, "Нет", "Нет", answer);
        Subject subject1 = new Subject(1L, "test", List.of(task1));

        when(personRepository.getPersonByToken(token)).thenReturn(List.of(person));
        when(answerMongoRepository.save(answerDocument1)).thenReturn(answerDocument);
        when(subjectRepository.findFirstByName(subject)).thenReturn(subject1);
        when(answerRepository.save(answer1)).thenReturn(answer);
        when(gradeRepository.save(grade)).thenReturn(grade);

        answerMongoService.createAnswerByToken(array, fileName, token, subject, task);

        verify(gradeRepository).save(argumentCaptorGrade.capture());
        assertEquals(grade, argumentCaptorGrade.getValue());
    }

    @Test
    void getDocument() {
        AnswerDocument answerDocument = new AnswerDocument("1L", "test", null, "test");

        when(answerMongoRepository.findById("1L")).thenReturn(Optional.of(answerDocument));

        assertEquals(answerDocument, answerMongoService.getDocument("1L"));
    }

    @Test
    void postDocument() {
        AnswerDocument answerDocument = new AnswerDocument(null, "fileName", null, "email");

        when(answerMongoRepository.save(answerDocument)).thenReturn(answerDocument);

        answerMongoService.postDocument(null,"fileName", "email");

        verify(answerMongoRepository).save(argumentCaptorAnswerDocument.capture());
        assertEquals(answerDocument, argumentCaptorAnswerDocument.getValue());
    }

    @Test
    void deleteDocument() {
        String id = "1L";

        answerMongoService.deleteDocument(id);

        verify(answerMongoRepository).deleteById(argumentCaptorStringId.capture());
        assertEquals(id, argumentCaptorStringId.getValue());
    }
}