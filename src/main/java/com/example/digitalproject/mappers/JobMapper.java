package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.models.entities.Job;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface JobMapper {
    JobGetDTO entityToGet(Job student);
    Job postToEntity(JobPostDTO studentPostDTO);
    Job putToEntity(JobPutDTO studentPutDTO);
    List<JobGetDTO> getAllJobs(List<Job> Jobs);
}
