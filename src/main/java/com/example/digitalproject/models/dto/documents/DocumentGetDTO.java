package com.example.digitalproject.models.dto.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentGetDTO {
    private Long id;
    private String description;
    private String documentMongoId;
}
