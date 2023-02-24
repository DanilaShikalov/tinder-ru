package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.grades.*;

import java.util.List;

public interface GradeService {
    GradeGetDTO getEntity(Long id);

    void postEntity(GradePostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(GradePutDTO putDTO);

    List<GradeGetDTO> getAllEntities();
}
