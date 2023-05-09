package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.TaskMapper;
import com.example.digitalproject.models.dto.tasks.*;
import com.example.digitalproject.models.entities.Subject;
import com.example.digitalproject.models.entities.Task;
import com.example.digitalproject.repositories.SubjectRepository;
import com.example.digitalproject.repositories.TaskRepository;
import com.example.digitalproject.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private SubjectRepository subjectRepository;

    @Override
    public TaskGetDTO getEntity(Long id) {
        return taskMapper.entityToGet(taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(TaskPostDTO taskPostDTO, Long idSubject) {
        Task task = taskMapper.postToEntity(taskPostDTO);
        task.setSubject(subjectRepository.findById(idSubject).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Subject не найден")));
        taskRepository.save(task);
    }

    @Override
    public List<TaskGetDTO> getTasksBySubject(String subject) {
        Subject subjectEntity = subjectRepository.findFirstByName(subject);
        return taskMapper.getAll(subjectEntity.getTasks());
    }

    @Override
    public void deleteEntity(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void putEntity(TaskPutDTO taskPutDTO, Long id) {
        Task task = taskMapper.putToEntity(taskPutDTO);
        task.setId(id);
        taskRepository.save(task);
    }

    @Override
    public List<TaskGetDTO> getAllEntities() {
        return taskMapper.getAll(taskRepository.findAll());
    }
}
