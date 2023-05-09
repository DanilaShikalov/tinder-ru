package com.example.digitalproject.models.dto.answers;

import com.example.digitalproject.models.dto.persons.PersonDefaultGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerWithPersonGetDTO {
    private LocalDate date;
    private PersonDefaultGetDTO person;
}
