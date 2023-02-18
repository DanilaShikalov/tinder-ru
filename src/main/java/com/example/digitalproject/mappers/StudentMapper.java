package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.students.*;
import com.example.digitalproject.models.entities.Student;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentGetDTO entityToGet(Student student);
    Student postToEntity(StudentPostDTO studentPostDTO);
    Student putToEntity(StudentPutDTO studentPutDTO);
    List<StudentGetDTO> getAllStudents(List<Student> Students);
}
