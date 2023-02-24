package com.example.digitalproject.services.servicesImpl;
import com.example.digitalproject.mappers.DocumentMapper;
import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.models.entities.Document;
import com.example.digitalproject.repositories.DocumentRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.DocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;
    private PersonRepository personRepository;

    @Override
    public DocumentGetDTO getEntity(Long id) {
        return documentMapper.entityToGet(documentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(DocumentPostDTO documentPostDTO, Long idPerson) {
        Document document = documentMapper.postToEntity(documentPostDTO);
        document.setPerson(personRepository.findById(idPerson).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person не найден")));
        documentRepository.save(document);
    }

    @Override
    public void deleteEntity(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public void putEntity(DocumentPutDTO documentPutDTO, Long id) {
        Document document = documentMapper.putToEntity(documentPutDTO);
        document.setId(id);
        documentRepository.save(document);
    }

    @Override
    public List<DocumentGetDTO> getAllEntities() {
        return documentMapper.getAll(documentRepository.findAll());
    }
}
