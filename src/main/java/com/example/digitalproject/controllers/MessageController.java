package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/messages")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Message", description = "Контроллер сообщений")
public class MessageController {
    private MessageService messageService;

    @GetMapping("/message/")
    @Operation(description = "Получить все сообщения")
    public ResponseEntity<List<MessageGetDTO>> getAllJobs(@RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        return ResponseEntity.ok(messageService.getAllMessages(list.get(0).substring("Bearer ".length())));
    }

    @PostMapping("/message/")
    @Operation(description = "Создать новое сообщение")
    public ResponseEntity<?> postMessage(@RequestBody MessagePostDTO messagePostDTO, @RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        messageService.postMessage(messagePostDTO, list.get(0).substring("Bearer ".length()));
        return ResponseEntity.ok().build();
    }
}
