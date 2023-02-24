package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.subjects.SubjectGetDTO;
import com.example.digitalproject.models.dto.subjects.SubjectPostDTO;
import com.example.digitalproject.models.dto.subjects.SubjectPutDTO;
import com.example.digitalproject.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subjects")
@AllArgsConstructor
public class SubjectController {
    private SubjectService subjectService;

    @GetMapping("/subject/{id}")
    public SubjectGetDTO getSubject(@PathVariable Long id) {
        return subjectService.getEntity(id);
    }

    @PostMapping("/subject/")
    public ResponseEntity<?> postSubject(@RequestBody SubjectPostDTO SubjectPostDTO) {
        subjectService.postEntity(SubjectPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/subject/")
    public ResponseEntity<?> putSubject(@RequestBody SubjectPutDTO SubjectPutDTO) {
        subjectService.putEntity(SubjectPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subject/")
    public List<SubjectGetDTO> getAllSubjects() {
        return subjectService.getAllEntities();
    }
}
