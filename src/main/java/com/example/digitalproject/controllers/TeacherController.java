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
@RequestMapping("api/teachers")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;

    @GetMapping("/teacher/{id}")
    public TeacherGetDTO getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping("/teacher/")
    public ResponseEntity<?> postTeacher(TeacherPostDTO teacherPostDTO) {
        teacherService.postTeacher(teacherPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/teacher/")
    public ResponseEntity<?> putTeacher(TeacherPutDTO teacherPutDTO) {
        teacherService.putTeacher(teacherPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teacher/")
    public List<TeacherGetDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
