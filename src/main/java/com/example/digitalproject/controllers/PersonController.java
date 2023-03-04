package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Person", description = "Контроллер пользователей")
public class PersonController {
    private PersonService personService;

    @GetMapping("/person/{id}")
    @Operation(description = "Получить пользователя по его id")
    public PersonGetDTO getPerson(@PathVariable Long id) {
        return personService.getEntity(id);
    }

    @PostMapping("/person/")
    @Operation(description = "Получить пользователя по его id")
    public ResponseEntity<?> postPerson(@RequestBody PersonPostDTO personPostDTO, @RequestParam Long idJob) {
        personService.postEntity(personPostDTO, idJob);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/person/addsub")
    @Operation(description = "Добавить предмет к пользователю")
    public ResponseEntity<?> addSub(@RequestParam Long idPerson, Long idSubject) {
        personService.addSubjectsToPerson(idPerson, idSubject);
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

    @GetMapping("/person/")
    @Operation(description = "Получить всех пользователей")
    public List<PersonGetDTO> getAllPersons() {
        return personService.getAllEntities();
    }
}
