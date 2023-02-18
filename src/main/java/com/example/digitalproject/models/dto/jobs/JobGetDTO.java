package com.example.digitalproject.models.dto.jobs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobGetDTO {
    private Long id;
    private String title;
    private Long salary;
    private List<String> responsibilities;
}
