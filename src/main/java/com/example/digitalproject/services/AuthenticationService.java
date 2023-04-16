package com.example.digitalproject.services;

import com.example.digitalproject.config.JwtUtils;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.*;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.TokenRepository;
import com.example.digitalproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PersonRepository personRepository;

    public User getUserByToken(String token) {
        Optional<Token> tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity.isPresent()) {
            return tokenEntity.get().getUser();
        }
        throw new ResponseStatusException(NOT_FOUND, "Not found user");
    }

    public void updateUserByToken(String token, String email, String password, String phone) {
        User user = getUserByToken(token);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        Person person = personRepository.getPersonByToken(token).get(0);
        person.setPhone(phone);
        person.setNumber(phone);
        personRepository.save(person);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new ResponseStatusException(CONFLICT, "This account already exists");
        }
        user = userRepository.save(user);
        var jwtToken = jwtUtils.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public RegisterResponse info(String token) {
        List<Token> list = tokenRepository.findAllByTokenAndExpiredFalseAndRevokedFalse(token);
        log.info(token);
        if (!list.isEmpty()) {
            User user = list.get(0).getUser();
            return new RegisterResponse(user.getFirstname(), user.getLastname(), user.getEmail());
        }
        throw new ResponseStatusException(CONFLICT, "Conflict");
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        var jwtToken = jwtUtils.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
