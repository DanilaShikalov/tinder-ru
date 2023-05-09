package com.example.digitalproject.controllers;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.services.AnswerMongoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("api/answers/mongo")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "digital-project")
@Tag(name = "AnswerMongo", description = "Контроллер для получения ответов в виде документов")
public class AnswerMongoController {
    private AnswerMongoService answerMongoService;

    @GetMapping("/answer/{id}")
    @Operation(summary = "Получить документ студента по его id")
    public ResponseEntity<AnswerDocument> getAnswer(@PathVariable String id) {
        return new ResponseEntity<>(answerMongoService.getDocument(id), OK);
    }

    @PostMapping(value = "/answer/", consumes = {MULTIPART_FORM_DATA_VALUE, TEXT_PLAIN_VALUE})
    @Operation(summary = "Создать новый документ студента")
    public ResponseEntity<?> postAnswer(@RequestParam MultipartFile multipartFile, @RequestParam String email) throws IOException {
        log.info(Arrays.toString(multipartFile.getBytes()));
        answerMongoService.postDocument(multipartFile.getBytes(), multipartFile.getOriginalFilename(), email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/answer/{id}")
    @Operation(summary = "Удалить документ студента по его id")
    public ResponseEntity<?> deleteAnswer(@PathVariable String id) {
        answerMongoService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/answer/", consumes = {MULTIPART_FORM_DATA_VALUE, TEXT_PLAIN_VALUE})
    @Operation(summary = "Изменить документ студента по его id")
    public ResponseEntity<?> putAnswer(@RequestParam MultipartFile multipartFile, @RequestParam String id, @RequestParam String email) throws IOException {
        log.info(Arrays.toString(multipartFile.getBytes()));
        answerMongoService.putDocument(multipartFile.getBytes(), id, multipartFile.getOriginalFilename(), email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/answer/")
    @Operation(summary = "Получить все документы студентов")
    public List<AnswerDocument> getAllAnswers() {
        return answerMongoService.getAllDocuments();
    }
}
