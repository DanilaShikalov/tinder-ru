package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.answers.AnswerDefaultGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerPostDTO;
import com.example.digitalproject.models.dto.answers.AnswerPutDTO;
import com.example.digitalproject.services.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answers")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Answer", description = "Контроллер для получения ответов")
public class AnswerController {
    private AnswerService answerService;

    @GetMapping("/answer/{id}")
    @Operation(summary = "Получить ответ студента по его id")
    public AnswerGetDTO getAnswer(@PathVariable Long id) {
        return answerService.getEntity(id);
    }

    @PostMapping("/answer/")
    @Operation(summary = "Создать новый ответ студента")
    public ResponseEntity<?> postAnswer(@RequestBody AnswerPostDTO answerPostDTO, @RequestParam Long idPerson, @RequestParam Long idTask) {
        answerService.postEntity(answerPostDTO, idPerson, idTask);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/answer/{id}")
    @Operation(summary = "Удалить ответ студента по его id")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {
        answerService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/answer/")
    @Operation(summary = "Изменить ответ студента по его id")
    public ResponseEntity<?> putAnswer(@RequestBody AnswerPutDTO answerPutDTO, @RequestParam Long id) {
        answerService.putEntity(answerPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/answer/")
    @Operation(summary = "Получить все ответы студентов")
    public List<AnswerGetDTO> getAllAnswers() {
        return answerService.getAllEntities();
    }

    @GetMapping("/answer/allwithtask")
    @Operation(summary = "Получить все ответы студентов с их заданиями")
    public List<AnswerDefaultGetDTO> getAllAnswersWithTask() {
        return answerService.getAllAnswersWithTask();
    }
}
