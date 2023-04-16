package com.example.digitalproject.models.entities;

import com.example.digitalproject.models.security.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String number;
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Document> documents;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Subject> subjects;
    @OneToOne(fetch = FetchType.EAGER)
    private Job job;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
