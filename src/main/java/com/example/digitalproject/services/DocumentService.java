package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.documents.*;

import java.util.List;

public interface DocumentService {
    DocumentGetDTO getDocument(Long id);

    void postDocument(DocumentPostDTO DocumentPostDTO);

    void deleteDocument(Long id);

    void putDocument(DocumentPutDTO DocumentPutDTO);

    List<DocumentGetDTO> getAllDocuments();
}
