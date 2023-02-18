package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.teachers.TeacherGetDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPostDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPutDTO;
import com.example.digitalproject.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v4")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;

    @GetMapping("teachers/{id}")
    public TeacherGetDTO getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping("teachers/")
    public ResponseEntity<?> postTeacher(TeacherPostDTO teacherPostDTO) {
        teacherService.postTeacher(teacherPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("teachers/")
    public ResponseEntity<?> putTeacher(TeacherPutDTO teacherPutDTO) {
        teacherService.putTeacher(teacherPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("teachers/")
    public List<TeacherGetDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
