package com.example.digitalproject.models.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String name;
    private String photo;
    private String email;
    private String message;
    private String date;
}
