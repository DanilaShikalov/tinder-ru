package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.subjects.*;
import com.example.digitalproject.models.entities.Subject;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectGetDTO entityToGet(Subject entity);
    Subject postToEntity(SubjectPostDTO postDTO);
    Subject putToEntity(SubjectPutDTO putDTO);
    List<SubjectGetDTO> getAll(List<Subject> list);

    List<SubjectWithTasksGetDTO> getAllSubjectsWithTasks(List<Subject> list);
}
