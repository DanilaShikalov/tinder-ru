package com.example.digitalproject.models.dto.teachers;

import com.example.digitalproject.models.dto.documents.DocumentGetDTO;
import com.example.digitalproject.models.dto.jobs.JobGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGetDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private JobGetDTO job;
    private List<DocumentGetDTO> documents;
}
