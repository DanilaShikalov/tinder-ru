package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.services.AuthenticationService;
import com.example.digitalproject.services.DocumentMongoService;
import com.example.digitalproject.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/persons")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Person", description = "Контроллер пользователей")
public class PersonController {
    private PersonService personService;

    private DocumentMongoService documentMongoService;

    private AuthenticationService authenticationService;

    @GetMapping("/person/{id}")
    @Operation(description = "Получить пользователя по его id")
    public PersonGetDTO getPerson(@PathVariable Long id) {
        return personService.getEntity(id);
    }

    @PostMapping("/person/")
    @Operation(description = "Получить пользователя по его id")
    public ResponseEntity<?> postPerson(@RequestBody PersonPostDTO personPostDTO, @RequestParam Long idUser) {
        personService.postEntity(personPostDTO, idUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/person/{id}")
    @Operation(description = "Удалить пользователя по его id")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/person/")
    @Operation(description = "Изменить пользователя по его id")
    public ResponseEntity<?> putPerson(@RequestBody PersonPutDTO personPutDTO, @RequestParam Long id) {
        personService.putEntity(personPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/person/email")
    @Operation(description = "Изменить почту пользователя")
    public void putPersonEmail(@RequestParam String email, @RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        personService.putPersonEmail(email, list.get(0).substring("Bearer ".length()));
    }

    @PutMapping("/person/password")
    @Operation(description = "Изменить почту пользователя")
    public void putPersonPass(@RequestParam String password, @RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        personService.putPersonPassword(password, list.get(0).substring("Bearer ".length()));
    }

    @PutMapping("/person/phone")
    @Operation(description = "Изменить почту пользователя")
    public void putPersonPhone(@RequestParam String phone, @RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        personService.putPersonPhone(phone, list.get(0).substring("Bearer ".length()));
    }

    @GetMapping("/person/")
    @Operation(description = "Получить всех пользователей")
    public List<PersonGetDTO> getAllPersons() {
        return personService.getAllEntities();
    }

    @GetMapping("/person/token")
    public ResponseEntity<PersonGetDTO> getPerson(@RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        return ResponseEntity.ok(personService.getPerson(list.get(0).substring("Bearer ".length())));
    }

    @GetMapping("/get/fullinfo")
    @Operation(description = "Получить полную информацию о пользователе")
    public ResponseEntity<PersonFullInfoGetDTO> getFullInfo(@RequestHeader HttpHeaders token) {
        List<String> list = token.get("Authorization");
        if (list == null || list.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Error");
        }
        return ResponseEntity.ok(personService.getFullInfo(list.get(0).substring("Bearer ".length())));
    }
}
