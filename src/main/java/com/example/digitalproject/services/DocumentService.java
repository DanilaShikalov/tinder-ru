package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.documents.*;

import java.util.List;

public interface DocumentService {
    DocumentGetDTO getEntity(Long id);

    void postEntity(DocumentPostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(DocumentPutDTO putDTO);

    List<DocumentGetDTO> getAllEntities();
}
