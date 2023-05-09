package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.grades.GradeGetDTO;
import com.example.digitalproject.models.dto.grades.GradePostDTO;
import com.example.digitalproject.models.dto.grades.GradePutDTO;
import com.example.digitalproject.services.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grades")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Grade", description = "Контроллер оценок студентов")
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/grade/{id}")
    @Operation(description = "Получить оценку по id")
    public GradeGetDTO getGrade(@PathVariable Long id) {
        return gradeService.getEntity(id);
    }

    @PostMapping("/grade/")
    @Operation(description = "Создать новую оценку")
    public ResponseEntity<?> postGrade(@RequestBody GradePostDTO gradePostDTO, @RequestParam Long idAnswer) {
        gradeService.postEntity(gradePostDTO, idAnswer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/grade/by/name/")
    @Operation(summary = "Сохранить оценку заданию")
    public ResponseEntity<?> setGrade(@RequestParam String subject, @RequestParam String task, @RequestParam String name,
                                      @RequestParam String surname, @RequestParam String grade) {
        gradeService.setGradeBySubjectAndTaskAndNameAndSurnme(subject, task, name, surname, grade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/grade/{id}")
    @Operation(description = "Удалить оценку по id")
    public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        gradeService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/grade/")
    @Operation(description = "Изменить оценку по id")
    public ResponseEntity<?> putGrade(@RequestBody GradePutDTO gradePutDTO, @RequestParam Long id) {
        gradeService.putEntity(gradePutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/grade/")
    @Operation(description = "Получить все оценки студентов")
    public List<GradeGetDTO> getAllGrades() {
        return gradeService.getAllEntities();
    }
}
