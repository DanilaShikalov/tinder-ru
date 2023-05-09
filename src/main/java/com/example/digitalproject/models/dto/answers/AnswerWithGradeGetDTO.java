package com.example.digitalproject.models.dto.answers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerWithGradeGetDTO {
    private byte[] bytes;
    private String grade;
}
