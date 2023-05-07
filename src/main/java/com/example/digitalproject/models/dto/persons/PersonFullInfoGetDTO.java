package com.example.digitalproject.models.dto.persons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonFullInfoGetDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Long salary;
    private String position;
    private String rating;
}
