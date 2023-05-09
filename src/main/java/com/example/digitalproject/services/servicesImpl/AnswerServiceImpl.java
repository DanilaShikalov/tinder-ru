package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.AnswerMapper;
import com.example.digitalproject.models.dto.answers.*;
import com.example.digitalproject.models.entities.Answer;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.entities.Subject;
import com.example.digitalproject.repositories.*;
import com.example.digitalproject.services.AnswerService;
import com.example.digitalproject.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;
    private TaskRepository taskRepository;
    private PersonRepository personRepository;
    private SubjectRepository subjectRepository;
    private AnswerMongoRepository answerMongoRepository;

    @Override
    public List<AnswerWithPersonGetDTO> getAnswersWithPerson(String name, String task) {
        Subject subject = subjectRepository.findFirstByName(name);
        return answerMapper.getAllAnswersWithPerson(subject.getTasks().stream().filter(x -> x.getTask().equals(task)).findFirst().get().getAnswers());
    }

    @Override
    public AnswerWithGradeGetDTO getAnswerWithGradeByToken(String subject, String task, String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        Answer answer = person.getAnswers().stream().filter(x -> x.getTask().getTask().equals(task) && x.getTask().getSubject().getName().equals(subject)).findFirst().get();
        return AnswerWithGradeGetDTO.builder()
                .grade(answer.getGrade().getGrade())
                .bytes(answerMongoRepository.findById(answer.getMongoId()).get().getBytes()).build();
    }

    @Override
    public AnswerWithGradeGetDTO getAnswerWithGradeByNameAndSurname(String subject, String task, String name, String surname) {
        Person person = personRepository.findFirstByNameAndSurname(name, surname);
        Answer answer = person.getAnswers().stream().filter(x -> x.getTask().getTask().equals(task) && x.getTask().getSubject().getName().equals(subject)).findFirst().get();
        return AnswerWithGradeGetDTO.builder()
                .grade(answer.getGrade().getGrade())
                .bytes(answerMongoRepository.findById(answer.getMongoId()).get().getBytes()).build();
    }

    @Override
    public AnswerGetDTO getEntity(Long id) {
        return answerMapper.entityToGet(answerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "answer не найден")));
    }

    @Override
    public void postEntity(AnswerPostDTO answerPostDTO, Long idPerson, Long idTask) {
        Answer answer = answerMapper.postToEntity(answerPostDTO);
        answer.setPerson(personRepository.findById(idPerson).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person не найден")));
        answer.setTask(taskRepository.findById(idTask).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task не найден")));
        answerRepository.save(answer);
    }

    @Override
    public void deleteEntity(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public void putEntity(AnswerPutDTO answerPutDTO, Long id) {
        Answer answer = answerMapper.putToEntity(answerPutDTO);
        answer.setId(id);
        answerRepository.save(answer);
    }

    @Override
    public List<AnswerGetDTO> getAllEntities() {
        return answerMapper.getAll(answerRepository.findAll());
    }

    @Override
    public List<AnswerDefaultGetDTO> getAllAnswersWithTask() {
        return answerMapper.getAllAnswersWithTasks(answerRepository.findAll());
    }
}
