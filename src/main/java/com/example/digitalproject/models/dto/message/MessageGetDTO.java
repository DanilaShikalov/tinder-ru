package com.example.digitalproject.models.dto.message;

import com.example.digitalproject.models.dto.persons.PersonGetDTO;
import com.example.digitalproject.models.security.RegisterResponse;
import com.example.digitalproject.models.security.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageGetDTO {
    private Long id;
    private String color;
    private String message;
    private LocalDateTime date;
    private RegisterResponse userFrom;
    private RegisterResponse userTo;
    private boolean statusMember;
}
