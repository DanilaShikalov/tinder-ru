package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.tasks.*;
import com.example.digitalproject.models.entities.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskGetDTO entityToGet(Task entity);
    Task postToEntity(TaskPostDTO postDTO);
    Task putToEntity(TaskPutDTO putDTO);
    List<TaskGetDTO> getAll(List<Task> list);
}
