package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.answers.*;
import com.example.digitalproject.models.entities.Answer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerGetDTO entityToGet(Answer entity);
    Answer postToEntity(AnswerPostDTO postDTO);
    Answer putToEntity(AnswerPutDTO putDTO);
    List<AnswerGetDTO> getAll(List<Answer> list);

    List<AnswerDefaultGetDTO> getAllAnswersWithTasks(List<Answer> list);
}
