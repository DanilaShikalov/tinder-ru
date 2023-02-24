package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.models.entities.Job;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface JobMapper {
    JobGetDTO entityToGet(Job entity);
    Job postToEntity(JobPostDTO postDTO);
    Job putToEntity(JobPutDTO putDTO);
    List<JobGetDTO> getAll(List<Job> list);
}
