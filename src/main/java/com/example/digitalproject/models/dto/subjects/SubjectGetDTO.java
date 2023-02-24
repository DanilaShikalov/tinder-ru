package com.example.digitalproject.models.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectGetDTO {
    private Long id;
    private String name;
}
