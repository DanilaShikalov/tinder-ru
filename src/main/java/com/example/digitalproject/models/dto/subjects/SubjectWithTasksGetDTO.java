package com.example.digitalproject.models.dto.subjects;

import com.example.digitalproject.models.dto.tasks.TaskGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectWithTasksGetDTO {
    private Long id;
    private String name;
    private List<TaskGetDTO> tasks;
}
