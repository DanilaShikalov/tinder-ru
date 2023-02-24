package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.TaskMapper;
import com.example.digitalproject.models.dto.tasks.*;
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

    @Override
    public TaskGetDTO getEntity(Long id) {
        return taskMapper.entityToGet(taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(TaskPostDTO TaskPostDTO) {
        taskRepository.save(taskMapper.postToEntity(TaskPostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void putEntity(TaskPutDTO TaskPutDTO) {
        taskRepository.save(taskMapper.putToEntity(TaskPutDTO));
    }

    @Override
    public List<TaskGetDTO> getAllEntities() {
        return taskMapper.getAll(taskRepository.findAll());
    }
}
