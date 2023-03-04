package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documents")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Document", description = "Контроллер для получения информации о документах")
public class DocumentController {
    private DocumentService documentService;

    @GetMapping("/document/{id}")
    @Operation(description = "Получить информацию о документу по его id")
    public DocumentGetDTO getDocument(@PathVariable Long id) {
        return documentService.getEntity(id);
    }

    @PostMapping("/document/")
    @Operation(description = "Создать новую информацию по документу")
    public ResponseEntity<?> postDocument(@RequestBody DocumentPostDTO documentPostDTO, @RequestParam Long idPerson) {
        documentService.postEntity(documentPostDTO, idPerson);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/document/{id}")
    @Operation(description = "Удалить информацию о документу по его id")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        documentService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/document/")
    @Operation(description = "Изменить информацию о документу по его id")
    public ResponseEntity<?> putDocument(@RequestBody DocumentPutDTO documentPutDTO, @RequestParam Long id) {
        documentService.putEntity(documentPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/")
    @Operation(description = "Получить всю информацию о всех документах")
    public List<DocumentGetDTO> getAllDocuments() {
        return documentService.getAllEntities();
    }
}
