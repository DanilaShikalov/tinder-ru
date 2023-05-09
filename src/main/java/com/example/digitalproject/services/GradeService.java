package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.grades.*;

import java.util.List;

public interface GradeService {
    GradeGetDTO getEntity(Long id);

    void postEntity(GradePostDTO postDTO, Long idAnswer);

    void deleteEntity(Long id);

    void putEntity(GradePutDTO putDTO, Long id);

    List<GradeGetDTO> getAllEntities();

    void setGradeBySubjectAndTaskAndNameAndSurnme(String subject, String task, String name, String surname, String grade);
}
