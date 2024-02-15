package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.persons.PersonSettingsPutDTO;
import com.example.digitalproject.models.security.AuthenticationRequest;
import com.example.digitalproject.models.security.AuthenticationResponse;
import com.example.digitalproject.models.security.RegisterRequest;
import com.example.digitalproject.models.security.RegisterResponse;
import com.example.digitalproject.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Контроллер регистрации и аутентификации")
public class AuthenticationController {
    private final AuthenticationService service;

//    @PostMapping("/register")
//    @Operation(description = "Зарегистрироваться и получить токен")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(service.register(request));
//    }

    @PostMapping("/authenticate")
    @Operation(description = "Войти и получить токен")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/update/info")
    @Operation(description = "Обновить инфу")
    @SecurityRequirement(name = "digital-project")
    public ResponseEntity<?> putInfoUser(@RequestBody PersonSettingsPutDTO putDTO, @RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        service.updateUserByToken(list.get(0).substring("Bearer ".length()), putDTO.getEmail(), putDTO.getPassword(), putDTO.getPhone());
        return ResponseEntity.ok("Все четко");
    }

    @GetMapping("/info")
    @Operation(description = "Получить информацию о user")
    @SecurityRequirement(name = "digital-project")
    public ResponseEntity<RegisterResponse> info(@RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        return ResponseEntity.ok(service.info(list.get(0).substring("Bearer ".length())));
    }
}
