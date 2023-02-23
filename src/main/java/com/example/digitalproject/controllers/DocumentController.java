package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.services.DocumentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documents")
@AllArgsConstructor
public class DocumentController {
    private DocumentService documentService;

    @GetMapping("/document/{id}")
    public DocumentGetDTO getDocument(@PathVariable Long id) {
        return documentService.getDocument(id);
    }

    @PostMapping("/document/")
    public ResponseEntity<?> postDocument(@RequestBody DocumentPostDTO DocumentPostDTO) {
        documentService.postDocument(DocumentPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/document/")
    public ResponseEntity<?> putDocument(@RequestBody DocumentPutDTO DocumentPutDTO) {
        documentService.putDocument(DocumentPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/")
    public List<DocumentGetDTO> getAllDocuments() {
        return documentService.getAllDocuments();
    }
}
