package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.teachers.*;
import com.example.digitalproject.models.entities.Teacher;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherGetDTO entityToGet(Teacher student);
    Teacher postToEntity(TeacherPostDTO studentPostDTO);
    Teacher putToEntity(TeacherPutDTO studentPutDTO);
    List<TeacherGetDTO> getAllTeachers(List<Teacher> Teachers);
}
