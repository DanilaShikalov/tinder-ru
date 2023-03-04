package com.example.digitalproject.controllers;

import com.example.digitalproject.models.security.AuthenticationRequest;
import com.example.digitalproject.models.security.AuthenticationResponse;
import com.example.digitalproject.models.security.RegisterRequest;
import com.example.digitalproject.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Контроллер регистрации и аутентификации")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(description = "Зарегистрироваться и получить токен")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    @Operation(description = "Войти и получить токен")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
