package com.example.digitalproject.models.documents;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDocument {
    @Id
    private String id;
    private String nameFile;
    private byte[] bytes;

    private String email;
}
