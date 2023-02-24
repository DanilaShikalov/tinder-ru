package com.example.digitalproject.models.dto.answers;

import com.example.digitalproject.models.dto.tasks.TaskGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDefaultGetDTO {
    private Long id;
    private LocalDate date;
    private String mongoId;
    private TaskGetDTO task;
}
