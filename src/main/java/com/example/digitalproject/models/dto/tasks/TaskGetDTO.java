package com.example.digitalproject.models.dto.tasks;

import com.example.digitalproject.models.entities.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskGetDTO {
    private Long id;
    private String task;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
