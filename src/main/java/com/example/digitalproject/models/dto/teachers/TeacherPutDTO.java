package com.example.digitalproject.models.dto.teachers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPutDTO {
    private String name;
    private String surname;
    private String phone;
}
