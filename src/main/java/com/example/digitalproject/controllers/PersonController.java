package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
@AllArgsConstructor
public class PersonController {
    private PersonService personService;

    @GetMapping("/person/{id}")
    public PersonGetDTO getPerson(@PathVariable Long id) {
        return personService.getEntity(id);
    }

    @PostMapping("/person/")
    public ResponseEntity<?> postPerson(@RequestBody PersonPostDTO personPostDTO, @RequestParam Long idJob) {
        personService.postEntity(personPostDTO, idJob);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/person/addsub")
    public ResponseEntity<?> addSub(@RequestParam Long idPerson, Long idSubject) {
        personService.addSubjectsToPerson(idPerson, idSubject);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/person/")
    public ResponseEntity<?> putPerson(@RequestBody PersonPutDTO personPutDTO, @RequestParam Long id) {
        personService.putEntity(personPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/person/")
    public List<PersonGetDTO> getAllPersons() {
        return personService.getAllEntities();
    }
}
