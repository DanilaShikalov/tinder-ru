package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.subjects.*;

import java.util.List;

public interface SubjectService {
    SubjectGetDTO getEntity(Long id);

    void postEntity(SubjectPostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(SubjectPutDTO putDTO, Long id);

    List<SubjectGetDTO> getAllEntities();

    List<SubjectWithTasksGetDTO> getAllSubjectsWithTasks();
}
