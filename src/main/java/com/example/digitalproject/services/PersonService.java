package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.persons.*;

import java.util.List;

public interface PersonService {
    PersonGetDTO getPerson(Long id);

    void postPerson(PersonPostDTO personPostDTO);

    void deletePerson(Long id);

    void putPerson(PersonPutDTO personPutDTO);

    List<PersonGetDTO> getAllPersons();

    void addDocumentsToPerson(Long idPerson, Long idsDocument);
}
