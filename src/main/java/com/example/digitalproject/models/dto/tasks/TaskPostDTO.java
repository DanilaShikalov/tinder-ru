package com.example.digitalproject.models.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskPostDTO {
    private String task;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
