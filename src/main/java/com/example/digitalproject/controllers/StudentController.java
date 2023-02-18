package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.students.*;
import com.example.digitalproject.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v3")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("students/{id}")
    public StudentGetDTO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping("students/")
    public ResponseEntity<?> postStudent(StudentPostDTO StudentPostDTO) {
        studentService.postStudent(StudentPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("students")
    public ResponseEntity<?> putStudent(StudentPutDTO StudentPutDTO) {
        studentService.putStudent(StudentPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("students")
    public List<StudentGetDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
