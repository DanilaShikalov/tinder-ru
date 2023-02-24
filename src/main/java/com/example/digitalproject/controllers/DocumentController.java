package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.services.DocumentService;
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
        return documentService.getEntity(id);
    }

    @PostMapping("/document/")
    public ResponseEntity<?> postDocument(@RequestBody DocumentPostDTO documentPostDTO, @RequestParam Long idPerson) {
        documentService.postEntity(documentPostDTO, idPerson);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        documentService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/document/")
    public ResponseEntity<?> putDocument(@RequestBody DocumentPutDTO documentPutDTO, @RequestParam Long id) {
        documentService.putEntity(documentPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/")
    public List<DocumentGetDTO> getAllDocuments() {
        return documentService.getAllEntities();
    }
}
