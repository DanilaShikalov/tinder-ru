package com.example.digitalproject.models.dto.jobs;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobPostDTO {
    private String title;
    private Long salary;
    private List<String> responsibilities;
}
