package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.tasks.*;

import java.util.List;

public interface TaskService {
    TaskGetDTO getEntity(Long id);

    void postEntity(TaskPostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(TaskPutDTO putDTO);

    List<TaskGetDTO> getAllEntities();
}
