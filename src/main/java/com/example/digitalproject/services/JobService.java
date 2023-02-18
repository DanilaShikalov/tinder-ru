package com.example.digitalproject.services;


import com.example.digitalproject.models.dto.jobs.*;

import java.util.List;

public interface JobService {
    JobGetDTO getJob(Long id);

    void postJob(JobPostDTO JobPostDTO);

    void deleteJob(Long id);

    void putJob(JobPutDTO JobPutDTO);

    List<JobGetDTO> getAllJobs();
}
