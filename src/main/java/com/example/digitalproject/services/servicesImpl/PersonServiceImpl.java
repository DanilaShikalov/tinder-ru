package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.PersonMapper;
import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.DocumentRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private PersonMapper personMapper;
    private DocumentRepository documentRepository;
    @Override
    public void addDocumentsToPerson(Long idPerson, Long idsDocument) {
        Person person = personRepository.findById(idPerson).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        person.getDocuments().add(documentRepository.findById(idsDocument).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
        personRepository.save(person);
    }

    @Override
    public PersonGetDTO getEntity(Long id) {
        return personMapper.entityToGet(personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(PersonPostDTO personPostDTO) {
        personRepository.save(personMapper.postToEntity(personPostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void putEntity(PersonPutDTO personPutDTO) {
        personRepository.save(personMapper.putToEntity(personPutDTO));
    }

    @Override
    public List<PersonGetDTO> getAllEntities() {
        return personMapper.getAll(personRepository.findAll());
    }
}
