package com.example.digitalproject.repositories;

import com.example.digitalproject.models.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findFirstByName(String name);
}
