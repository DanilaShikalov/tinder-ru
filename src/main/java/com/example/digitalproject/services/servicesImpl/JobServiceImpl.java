package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.JobMapper;
import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.repositories.JobRepository;
import com.example.digitalproject.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper;

    @Override
    public JobGetDTO getJob(Long id) {
        return jobMapper.entityToGet(jobRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postJob(JobPostDTO jobPostDTO) {
        jobRepository.save(jobMapper.postToEntity(jobPostDTO));
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void putJob(JobPutDTO jobPutDTO) {
        jobRepository.save(jobMapper.putToEntity(jobPutDTO));
    }

    @Override
    public List<JobGetDTO> getAllJobs() {
        return jobMapper.getAllJobs(jobRepository.findAll());
    }
}
