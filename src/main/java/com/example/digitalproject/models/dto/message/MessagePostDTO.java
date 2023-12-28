package com.example.digitalproject.models.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagePostDTO {
    private String color;
    private String message;
    private LocalDateTime date;
    private String emailFrom;
    private String emailTo;
}
