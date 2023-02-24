package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.grades.GradeGetDTO;
import com.example.digitalproject.models.dto.grades.GradePostDTO;
import com.example.digitalproject.models.dto.grades.GradePutDTO;
import com.example.digitalproject.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grades")
@AllArgsConstructor
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/grade/{id}")
    public GradeGetDTO getGrade(@PathVariable Long id) {
        return gradeService.getEntity(id);
    }

    @PostMapping("/grade/")
    public ResponseEntity<?> postGrade(@RequestBody GradePostDTO gradePostDTO, @RequestParam Long idAnswer) {
        gradeService.postEntity(gradePostDTO, idAnswer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        gradeService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/grade/")
    public ResponseEntity<?> putGrade(@RequestBody GradePutDTO gradePutDTO, @RequestParam Long id) {
        gradeService.putEntity(gradePutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/grade/")
    public List<GradeGetDTO> getAllGrades() {
        return gradeService.getAllEntities();
    }
}
