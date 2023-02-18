package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.documents.*;
import com.example.digitalproject.models.entities.Document;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentGetDTO entityToGet(Document student);
    Document postToEntity(DocumentPostDTO studentPostDTO);
    Document putToEntity(DocumentPutDTO studentPutDTO);
    List<DocumentGetDTO> getAllDocuments(List<Document> documents);
}
