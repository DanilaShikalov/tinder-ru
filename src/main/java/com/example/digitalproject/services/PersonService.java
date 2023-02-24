package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.persons.*;

import java.util.List;

public interface PersonService {
    PersonGetDTO getEntity(Long id);

    void postEntity(PersonPostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(PersonPutDTO putDTO);

    List<PersonGetDTO> getAllEntities();

    void addDocumentsToPerson(Long idPerson, Long idsDocument);
}
