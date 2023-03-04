package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.DocumentMapper;
import com.example.digitalproject.models.dto.documents.DocumentGetDTO;
import com.example.digitalproject.models.entities.Document;
import com.example.digitalproject.repositories.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {
    @Mock
    DocumentRepository documentRepository;
    @Mock
    DocumentMapper documentMapper;
    @InjectMocks
    DocumentServiceImpl documentService;

    @Test
    void getDocument() {
        Document document = new Document(1L, "test", "test", null);
        DocumentGetDTO documentGetDTO = new DocumentGetDTO(1L, "test", "test");

        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(documentMapper.entityToGet(document)).thenReturn(documentGetDTO);

        assertEquals(documentGetDTO, documentService.getEntity(1L));
        verify(documentRepository).findById(1L);
        verify(documentMapper).entityToGet(document);
    }

    @Test
    void postDocument() {
    }

    @Test
    void deleteDocument() {
    }

    @Test
    void putDocument() {
    }

    @Test
    void getAllDocuments() {
    }
}