package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.grades.*;
import com.example.digitalproject.models.entities.Grade;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GradeMapper {
    GradeGetDTO entityToGet(Grade entity);
    Grade postToEntity(GradePostDTO postDTO);
    Grade putToEntity(GradePutDTO putDTO);
    List<GradeGetDTO> getAll(List<Grade> list);
}
