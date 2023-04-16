package com.example.digitalproject.models.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDocument {
    @Id
    private String id;
    private String nameFile;
    private byte[] bytes;

    private String email;
}
