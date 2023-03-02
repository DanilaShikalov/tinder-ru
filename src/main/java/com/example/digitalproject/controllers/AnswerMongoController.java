package com.example.digitalproject.controllers;

import com.example.digitalproject.models.documents.AnswerDocument;
import com.example.digitalproject.services.AnswerMongoService;
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

@RestController
@RequestMapping("api/answers/mongo")
@AllArgsConstructor
@Slf4j
public class AnswerMongoController {
    private AnswerMongoService answerMongoService;

    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerDocument> getAnswer(@PathVariable String id) {
        return new ResponseEntity<>(answerMongoService.getDocument(id), OK);
    }

    @PostMapping(value = "/answer/", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postAnswer(@RequestParam MultipartFile multipartFile) throws IOException {
        log.info(Arrays.toString(multipartFile.getBytes()));
        answerMongoService.postDocument(multipartFile.getBytes());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable String id) {
        answerMongoService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/answer/")
    public ResponseEntity<?> putAnswer(@RequestParam MultipartFile multipartFile, @RequestParam String id) throws IOException {
        answerMongoService.putDocument(multipartFile.getBytes(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/answer/")
    public List<AnswerDocument> getAllAnswers() {
        return answerMongoService.getAllDocuments();
    }
}
