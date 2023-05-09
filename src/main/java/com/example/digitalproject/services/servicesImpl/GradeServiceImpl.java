package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.GradeMapper;
import com.example.digitalproject.models.dto.answers.AnswerWithGradeGetDTO;
import com.example.digitalproject.models.dto.grades.GradeGetDTO;
import com.example.digitalproject.models.dto.grades.GradePostDTO;
import com.example.digitalproject.models.dto.grades.GradePutDTO;
import com.example.digitalproject.models.entities.Answer;
import com.example.digitalproject.models.entities.Grade;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.AnswerRepository;
import com.example.digitalproject.repositories.GradeRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private GradeRepository gradeRepository;
    private GradeMapper gradeMapper;
    private AnswerRepository answerRepository;
    private PersonRepository personRepository;

    @Override
    public GradeGetDTO getEntity(Long id) {
        return gradeMapper.entityToGet(gradeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void setGradeBySubjectAndTaskAndNameAndSurnme(String subject, String task, String name, String surname, String grade) {
        Person person = personRepository.findFirstByNameAndSurname(name, surname);
        Answer answer = person.getAnswers().stream().filter(x -> x.getTask().getTask().equals(task) && x.getTask().getSubject().getName().equals(subject)).findFirst().get();
        Grade gradeEntity = answer.getGrade();
        gradeEntity.setAnswer(answer);
        gradeEntity.setGrade(grade);
        gradeRepository.save(gradeEntity);
    }

    @Override
    public void postEntity(GradePostDTO gradePostDTO, Long idAnswer) {
        Grade grade = gradeMapper.postToEntity(gradePostDTO);
        grade.setAnswer(answerRepository.findById(idAnswer).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Answer не найден")));
        gradeRepository.save(grade);
    }

    @Override
    public void deleteEntity(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public void putEntity(GradePutDTO gradePutDTO, Long id) {
        Grade grade = gradeMapper.putToEntity(gradePutDTO);
        grade.setId(id);
        gradeRepository.save(grade);
    }

    @Override
    public List<GradeGetDTO> getAllEntities() {
        return gradeMapper.getAll(gradeRepository.findAll());
    }
}
