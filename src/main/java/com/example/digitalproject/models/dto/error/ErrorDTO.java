package com.example.digitalproject.models.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private HttpStatusCode status;
    private Integer code;
    private String description;
    private LocalDateTime date;
}
