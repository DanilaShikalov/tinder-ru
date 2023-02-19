package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.students.*;

import java.util.List;

public interface StudentService {
    StudentGetDTO getStudent(Long id);

    void postStudent(StudentPostDTO StudentPostDTO);

    void deleteStudent(Long id);

    void putStudent(StudentPutDTO StudentPutDTO);

    List<StudentGetDTO> getAllStudents();

    void addDocumentsToStudent(Long idStudent, Long idsDocument);
}
