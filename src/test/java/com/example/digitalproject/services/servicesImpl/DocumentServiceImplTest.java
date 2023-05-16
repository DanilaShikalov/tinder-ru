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
    DocumentRepository documentRepository; // обязательно, чтобы задать условия на этот объект
    @Mock
    DocumentMapper documentMapper;
    @InjectMocks
    DocumentServiceImpl documentService; // вносим все mock объекты, которые нужны, внутрь этого объекта

    @Test
    void getDocument() {
        Document document = new Document(1L, "test", "test", null); // создаем то, что наш сервис вернет
        DocumentGetDTO documentGetDTO = new DocumentGetDTO(1L, "test", "test"); // опционально

        when(documentRepository.findById(1L)).thenReturn(Optional.of(document)); // задаем условие (когда documentRepository вызовет findById(1L), то вернется document
        when(documentMapper.entityToGet(document)).thenReturn(documentGetDTO); // опционально, условия на маппер (парсинг)

        assertEquals(documentGetDTO, documentService.getEntity(1L)); // вызываем у сервиса получения данных.
        // Внутри вызванного метода вызывается documentRepository.findById из БД, но, так как мы поставили условие раннее, то вызова из БД не будет, а будет возвращен объект document
        // Далее сравниваем ожидаемые данные с полученными
        verify(documentRepository).findById(1L); // опционально
        verify(documentMapper).entityToGet(document); // опционально
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