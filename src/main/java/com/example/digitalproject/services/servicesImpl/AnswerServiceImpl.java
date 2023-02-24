package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.AnswerMapper;
import com.example.digitalproject.models.dto.answers.AnswerDefaultGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerPostDTO;
import com.example.digitalproject.models.dto.answers.AnswerPutDTO;
import com.example.digitalproject.models.entities.Answer;
import com.example.digitalproject.repositories.AnswerRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.TaskRepository;
import com.example.digitalproject.services.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;
    private TaskRepository taskRepository;
    private PersonRepository personRepository;

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
