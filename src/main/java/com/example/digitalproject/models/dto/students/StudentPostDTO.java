package com.example.digitalproject.models.dto.students;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPostDTO {
    private String name;
    private String surname;
    private String phone;
    private String number;
    private List<Long> document_ids;
}
