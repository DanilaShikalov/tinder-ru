package com.example.digitalproject.models.dto.grades;

import com.example.digitalproject.models.entities.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradePutDTO {
    private String grade;
    private String description;
    private Answer answer;
}
