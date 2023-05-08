package com.example.digitalproject.controllers;

import com.example.digitalproject.models.documents.DocumentDocument;
import com.example.digitalproject.services.AuthenticationService;
import com.example.digitalproject.services.DocumentMongoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("api/documents/mongo")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "digital-project")
public class DocumentMongoController {
    private DocumentMongoService documentMongoService;
    private AuthenticationService authenticationService;

    @PostMapping(value = "/document/", consumes = {MULTIPART_FORM_DATA_VALUE, TEXT_PLAIN_VALUE})
    @Operation(summary = "Создать новый документ студента")
    public ResponseEntity<?> postAnswer(@RequestParam MultipartFile multipartFile, @RequestHeader HttpHeaders token) throws IOException {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        documentMongoService.postDocument(multipartFile.getBytes(), multipartFile.getOriginalFilename(),
                authenticationService.info(list.get(0).substring("Bearer ".length())).getEmail(), list.get(0).substring("Bearer ".length()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getall/")
    @Operation(description = "Получить все документы по токену")
    public ResponseEntity<List<DocumentDocument>> getAllDocumentsByToken(@RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        return ResponseEntity.ok(documentMongoService.getAllDocumentsByToken(list.get(0).substring("Bearer ".length())));
    }
}
