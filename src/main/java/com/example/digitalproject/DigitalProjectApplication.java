package com.example.digitalproject;

import com.example.digitalproject.models.dto.answers.AnswerPostDTO;
import com.example.digitalproject.models.dto.documents.DocumentPostDTO;
import com.example.digitalproject.models.dto.grades.GradePostDTO;
import com.example.digitalproject.models.dto.jobs.JobPostDTO;
import com.example.digitalproject.models.dto.persons.PersonPostDTO;
import com.example.digitalproject.models.dto.subjects.SubjectPostDTO;
import com.example.digitalproject.models.dto.tasks.TaskPostDTO;
import com.example.digitalproject.models.security.RegisterRequest;
import com.example.digitalproject.services.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Digital API", version = "1337.228", description = "Educational platform"))
@SecurityScheme(name = "digital-project", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class DigitalProjectApplication implements CommandLineRunner {
    @Autowired
    private PersonService personService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private JobService jobService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private GradeService gradeService;

    @Autowired
    private AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jobService.postEntity(new JobPostDTO("учитель", 60_000L, List.of("учить", "убирать")));
        jobService.postEntity(new JobPostDTO("уборщик", 20_000L, List.of("убирать", "выкидывать мусор")));

        authenticationService.register(new RegisterRequest("a", "a", "a", "a"));
        authenticationService.register(new RegisterRequest("Петя", "Пупкин", "g2@gmail.gog", "qwerty123"));
        authenticationService.register(new RegisterRequest("Петя", "Пупкин", "g3@gmail.gog", "qwerty123"));


        personService.postEntity(new PersonPostDTO("Петя", "Пупкин", "88005553535", "хз что это"), 1L, 1L);
        personService.postEntity(new PersonPostDTO("Даша", "Попова", "342567", "цпцпц"), 2L, 2L);
        personService.postEntity(new PersonPostDTO("Бывшая", "Бывшая", "зачем знать", "лох"), 2L, 3L);

        documentService.postEntity(new DocumentPostDTO("паспорт", "что-то"), 1L);
        documentService.postEntity(new DocumentPostDTO("паспорт", "что-то"), 2L);
        documentService.postEntity(new DocumentPostDTO("паспорт", "что-то"), 3L);
        documentService.postEntity(new DocumentPostDTO("сертификат шлюхи", "спид"), 3L);

        subjectService.postEntity(new SubjectPostDTO("Матеша"));
        subjectService.postEntity(new SubjectPostDTO("Химия"));
        subjectService.postEntity(new SubjectPostDTO("Физика"));
        subjectService.postEntity(new SubjectPostDTO("Физра"));

        personService.addSubjectsToPerson(1L, 1L);
        personService.addSubjectsToPerson(1L, 2L);
        personService.addSubjectsToPerson(1L, 3L);

        personService.addSubjectsToPerson(2L, 1L);
        personService.addSubjectsToPerson(2L, 4L);

        personService.addSubjectsToPerson(3L, 1L);
        personService.addSubjectsToPerson(3L, 2L);
        personService.addSubjectsToPerson(3L, 3L);
        personService.addSubjectsToPerson(3L, 4L);

        taskService.postEntity(new TaskPostDTO("номера 1-5", LocalDate.now(), LocalDate.now().plusDays(7)), 1L);
        taskService.postEntity(new TaskPostDTO("лаба 1", LocalDate.now(), LocalDate.now().plusDays(7)), 2L);
        taskService.postEntity(new TaskPostDTO("номера 1-123", LocalDate.now(), LocalDate.now().plusDays(7)), 3L);
        taskService.postEntity(new TaskPostDTO("принести собаку", LocalDate.now(), LocalDate.now().plusDays(7)), 3L);
        taskService.postEntity(new TaskPostDTO("10000 отжиманий", LocalDate.now(), LocalDate.now().plusDays(7)), 4L);

        answerService.postEntity(new AnswerPostDTO(LocalDate.now(), "я не сделал"), 1L, 1L);
        answerService.postEntity(new AnswerPostDTO(LocalDate.now(), "вот"), 1L, 3L);
        answerService.postEntity(new AnswerPostDTO(LocalDate.now(), "минус 40 кг"), 2L, 5L);
        answerService.postEntity(new AnswerPostDTO(LocalDate.now(), "я спидозная"), 3L, 4L);
        answerService.postEntity(new AnswerPostDTO(LocalDate.now(), "я кидало"), 3L, 2L);

        gradeService.postEntity(new GradePostDTO("Два", "Эх"), 1L);
        gradeService.postEntity(new GradePostDTO("Отлично", "Сделано супер"), 2L);
        gradeService.postEntity(new GradePostDTO("Отлично", "Вау"), 3L);
        gradeService.postEntity(new GradePostDTO("Ты тупая", "Ты тупая"), 4L);
        gradeService.postEntity(new GradePostDTO("Лох", "Лох"), 5L);
    }
}
