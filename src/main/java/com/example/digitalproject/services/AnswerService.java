package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.answers.*;

import java.util.List;

public interface AnswerService {
    AnswerGetDTO getEntity(Long id);

    void postEntity(AnswerPostDTO postDTO, Long idPerson, Long idTask);

    void deleteEntity(Long id);

    void putEntity(AnswerPutDTO putDTO, Long id);

    List<AnswerGetDTO> getAllEntities();

    List<AnswerDefaultGetDTO> getAllAnswersWithTask();

    List<AnswerWithPersonGetDTO> getAnswersWithPerson(String name, String task);

    AnswerWithGradeGetDTO getAnswerWithGradeByToken(String subject, String task, String token);

    AnswerWithGradeGetDTO getAnswerWithGradeByNameAndSurname(String subject, String task, String name, String surname);
}
