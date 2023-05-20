package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.AnswerMapper;
import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.models.dto.answers.AnswerWithGradeGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerWithPersonGetDTO;
import com.example.digitalproject.models.entities.*;
import com.example.digitalproject.repositories.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private AnswerMapper answerMapper;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private AnswerMongoRepository answerMongoRepository;
    @InjectMocks
    private AnswerServiceImpl answerServiceImpl;

    @Test
    void getAnswerWithGradeByToken() {
        String token = "test";
        List<Answer> answers = List.of(new Answer(null, null, "id1", null, new Grade(null, "null", null, null), new Task(null, "test", null, null, null, new Subject(null, "test", null))));
        Person person = new Person(null, null, null, null,
                null, null, answers, null, null, null);
        Answer answer = new Answer(null, null, "id1", null, new Grade(null, "null", null, null), new Task(null, "test", null, null, null, new Subject(null, "test", null)));
        AnswerDocument answerDocument = new AnswerDocument(null, null, new byte[]{1}, null);
        AnswerWithGradeGetDTO answerWithGradeGetDTO = AnswerWithGradeGetDTO.builder()
                .grade(answer.getGrade().getGrade())
                .bytes(new byte[]{1}).build();

        when(personRepository.getPersonByToken(token)).thenReturn(List.of(person));
        when(answerMongoRepository.findById("id1")).thenReturn(Optional.of(answerDocument));

        assertEquals(answerWithGradeGetDTO, answerServiceImpl.getAnswerWithGradeByToken("test", "test", "test"));
    }

    @Test
    void getAnswerWithGradeByNameAndSurname() {
        String name = "test";
        String surname = "test";
        List<Answer> answers = List.of(new Answer(null, null, "id1", null, new Grade(null, "null", null, null), new Task(null, "test", null, null, null, new Subject(null, "test", null))));
        Person person = new Person(null, null, null, null,
                null, null, answers, null, null, null);
        Answer answer = new Answer(null, null, "id1", null, new Grade(null, "null", null, null), new Task(null, "test", null, null, null, new Subject(null, "test", null)));
        AnswerDocument answerDocument = new AnswerDocument(null, null, new byte[]{1}, null);
        AnswerWithGradeGetDTO answerWithGradeGetDTO = AnswerWithGradeGetDTO.builder()
                .grade(answer.getGrade().getGrade())
                .bytes(new byte[]{1}).build();

        when(personRepository.findFirstByNameAndSurname(name, surname)).thenReturn(person);
        when(answerMongoRepository.findById("id1")).thenReturn(Optional.of(answerDocument));

        assertEquals(answerWithGradeGetDTO, answerServiceImpl.getAnswerWithGradeByNameAndSurname("test", "test", "test", "test"));
    }
}