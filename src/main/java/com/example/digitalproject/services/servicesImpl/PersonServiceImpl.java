package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.PersonMapper;
import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.DocumentRepository;
import com.example.digitalproject.repositories.JobRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.SubjectRepository;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private PersonMapper personMapper;
    private DocumentRepository documentRepository;
    private JobRepository jobRepository;
    private SubjectRepository subjectRepository;

    @Override
    public void addSubjectsToPerson(Long idPerson, Long idSubject) {
        Person person = personRepository.findById(idPerson).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person не найден"));
        person.getSubjects().add(subjectRepository.findById(idSubject).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Subject не найден")));
        personRepository.save(person);
    }

    @Override
    public PersonGetDTO getEntity(Long id) {
        return personMapper.entityToGet(personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(PersonPostDTO personPostDTO, Long idJob) {
        Person person = personMapper.postToEntity(personPostDTO);
        person.setJob(jobRepository.findById(idJob).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Нет такого job")));
        personRepository.save(person);
    }

    @Override
    public void deleteEntity(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void putEntity(PersonPutDTO personPutDTO, Long id) {
        Person person = personMapper.putToEntity(personPutDTO);
        person.setId(id);
        personRepository.save(person);
    }

    @Override
    public List<PersonGetDTO> getAllEntities() {
        return personMapper.getAll(personRepository.findAll());
    }
}
