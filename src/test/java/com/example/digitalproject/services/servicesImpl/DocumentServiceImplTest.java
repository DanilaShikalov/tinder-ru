package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.DocumentMapper;
import com.example.digitalproject.models.dto.documents.DocumentGetDTO;
import com.example.digitalproject.models.dto.documents.DocumentPostDTO;
import com.example.digitalproject.models.dto.documents.DocumentPutDTO;
import com.example.digitalproject.models.entities.Document;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.DocumentRepository;
import com.example.digitalproject.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {
    @Mock
    private DocumentRepository documentRepository;
    @Mock
    private DocumentMapper documentMapper;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private DocumentServiceImpl documentService;
    @Captor
    private ArgumentCaptor<Long> argumentCaptorId;
    @Captor
    private ArgumentCaptor<Document> argumentCaptorDocument;

    @Test
    void getEntity() {
        // given
        Document document = new Document(1L, "test", "test", null);
        DocumentGetDTO documentGetDTO = new DocumentGetDTO(1L, "test", "test");

        // when
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(documentMapper.entityToGet(document)).thenReturn(documentGetDTO);

        // then
        assertEquals(documentGetDTO, documentService.getEntity(1L));
        verify(documentRepository).findById(1L);
        verify(documentMapper).entityToGet(document);
    }

    @Test
    void postEntity() {
        // given
        Person person = new Person();
        Document document = new Document(1L, "test", "test", null);
        DocumentPostDTO documentPostDTO = new DocumentPostDTO("test", "test");
        Document documentPost = new Document(null, "test", "test", null);
        Long id = 1L;

        // when
        when(documentMapper.postToEntity(documentPostDTO)).thenReturn(documentPost);
        when(documentRepository.save(documentPost)).thenReturn(document);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        documentService.postEntity(documentPostDTO, id);

        // then
        verify(documentMapper).postToEntity(documentPostDTO);
        verify(documentRepository).save(documentPost);
        verify(documentRepository).save(argumentCaptorDocument.capture());
        assertEquals(documentPost, argumentCaptorDocument.getValue());
    }

    @Test
    void deleteEntity() {
        documentService.deleteEntity(1L);

        verify(documentRepository).deleteById(argumentCaptorId.capture());
        assertEquals(1L, argumentCaptorId.getValue());
    }

    @Test
    void putEntity() {
        DocumentPutDTO documentPutDTO = new DocumentPutDTO();
        Document document = new Document(null, "test", "test", null);
        Long id = 1L;
        Document documentPut = new Document(id, "test", "test", null);

        when(documentMapper.putToEntity(documentPutDTO)).thenReturn(document);

        documentService.putEntity(documentPutDTO, id);
        verify(documentRepository).save(argumentCaptorDocument.capture());
        assertEquals(documentPut, argumentCaptorDocument.getValue());

    }

    @Test
    void getAllEntities() {
        DocumentGetDTO documentGetDTO1 = new DocumentGetDTO(1L, "test", "test");
        DocumentGetDTO documentGetDTO2 = new DocumentGetDTO(2L, "test", "test");
        DocumentGetDTO documentGetDTO3 = new DocumentGetDTO(3L, "test", "test");
        List<DocumentGetDTO> list = List.of(documentGetDTO1, documentGetDTO2, documentGetDTO3);

        Document document1 = new Document(1L, "test", "test", null);
        Document document2 = new Document(2L, "test", "test", null);
        Document document3 = new Document(3L, "test", "test", null);
        List<Document> listEn = List.of(document1, document2, document3);

        when(documentRepository.findAll()).thenReturn(listEn);
        when(documentMapper.getAll(listEn)).thenReturn(list);

        assertEquals(list, documentService.getAllEntities());
    }
}