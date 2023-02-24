package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.answers.*;
import com.example.digitalproject.services.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answers")
@AllArgsConstructor
public class AnswerController {
    private AnswerService answerService;

    @GetMapping("/answer/{id}")
    public AnswerGetDTO getAnswer(@PathVariable Long id) {
        return answerService.getEntity(id);
    }

    @PostMapping("/answer/")
    public ResponseEntity<?> postAnswer(@RequestBody AnswerPostDTO AnswerPostDTO) {
        answerService.postEntity(AnswerPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {
        answerService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/answer/")
    public ResponseEntity<?> putAnswer(@RequestBody AnswerPutDTO AnswerPutDTO) {
        answerService.putEntity(AnswerPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/answer/")
    public List<AnswerGetDTO> getAllAnswers() {
        return answerService.getAllEntities();
    }
}
