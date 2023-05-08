package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.documents.*;

import java.util.List;

public interface DocumentService {
    DocumentGetDTO getEntity(Long id);

    void postEntity(DocumentPostDTO postDTO, Long idPerson);

    void deleteEntity(Long id);

    void putEntity(DocumentPutDTO putDTO, Long id);

    List<DocumentGetDTO> getAllEntities();


}
