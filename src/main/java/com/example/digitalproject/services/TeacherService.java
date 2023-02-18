package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.teachers.TeacherGetDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPostDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPutDTO;

import java.util.List;

public interface TeacherService {
    TeacherGetDTO getTeacher(Long id);

    void postTeacher(TeacherPostDTO teacherPostDTO);

    void deleteTeacher(Long id);

    void putTeacher(TeacherPutDTO teacherPutDTO);

    List<TeacherGetDTO> getAllTeachers();
}
