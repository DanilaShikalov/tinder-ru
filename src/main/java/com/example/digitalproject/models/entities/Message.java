package com.example.digitalproject.models.entities;


import com.example.digitalproject.models.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private String message;
    private LocalDateTime date;

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;
}
