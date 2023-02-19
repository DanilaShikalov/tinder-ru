package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.students.*;
import com.example.digitalproject.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public StudentGetDTO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/student/")
    public ResponseEntity<?> postStudent(StudentPostDTO StudentPostDTO) {
        studentService.postStudent(StudentPostDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student/adddocs")
    public ResponseEntity<?> addDocsToStudent(@RequestParam Long idStudent, @RequestParam Long idsDocument) {
        studentService.addDocumentsToStudent(idStudent, idsDocument);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/student/")
    public ResponseEntity<?> putStudent(StudentPutDTO StudentPutDTO) {
        studentService.putStudent(StudentPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/")
    public List<StudentGetDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
