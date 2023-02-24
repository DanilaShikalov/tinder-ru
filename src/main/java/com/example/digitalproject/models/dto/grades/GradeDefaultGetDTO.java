package com.example.digitalproject.models.dto.grades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDefaultGetDTO {
    private Long id;
    private String grade;
    private String description;
}
