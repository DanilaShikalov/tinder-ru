package com.example.digitalproject.models.dto.students;

import com.example.digitalproject.models.dto.documents.DocumentGetDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String number;
    private List<DocumentGetDTO> documents;
}
