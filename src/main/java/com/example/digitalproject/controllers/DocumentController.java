package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1")
@AllArgsConstructor
public class DocumentController {
    private DocumentService documentService;

    @GetMapping("/documents/{id}")
    public DocumentGetDTO getDocument(@PathVariable Long id) {
        return documentService.getDocument(id);
    }

    @PostMapping("/documents")
    public ResponseEntity<?> postDocument(DocumentPostDTO DocumentPostDTO) {
        documentService.postDocument(DocumentPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/documents")
    public ResponseEntity<?> putDocument(DocumentPutDTO DocumentPutDTO) {
        documentService.putDocument(DocumentPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/documents")
    public List<DocumentGetDTO> getAllDocuments() {
        return documentService.getAllDocuments();
    }
}
