package com.example.digitalproject.models.dto.answers;

import com.example.digitalproject.models.dto.grades.GradeGetDTO;
import com.example.digitalproject.models.dto.persons.PersonGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerGetDTO {
    private Long id;
    private LocalDate date;
    private String mongoId;
    private PersonGetDTO person;
    private GradeGetDTO grade;
}
