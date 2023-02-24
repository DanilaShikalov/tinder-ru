package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.tasks.*;

import java.util.List;

public interface TaskService {
    TaskGetDTO getEntity(Long id);

    void postEntity(TaskPostDTO postDTO, Long idSubject);

    void deleteEntity(Long id);

    void putEntity(TaskPutDTO putDTO, Long id);

    List<TaskGetDTO> getAllEntities();
}
