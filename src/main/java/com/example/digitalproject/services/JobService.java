package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.jobs.*;

import java.util.List;

public interface JobService {
    JobGetDTO getEntity(Long id);

    void postEntity(JobPostDTO postDTO);

    void deleteEntity(Long id);

    void putEntity(JobPutDTO putDTO);

    List<JobGetDTO> getAllEntities();
}
