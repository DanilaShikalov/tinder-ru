package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v2")
@AllArgsConstructor
public class JobController {
    private JobService jobService;

    @GetMapping("/jobs/{id}")
    public JobGetDTO getJob(@PathVariable Long id) {
        return jobService.getJob(id);
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> postJob(JobPostDTO JobPostDTO) {
        jobService.postJob(JobPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/jobs")
    public ResponseEntity<?> putJob(JobPutDTO JobPutDTO) {
        jobService.putJob(JobPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/jobs")
    public List<JobGetDTO> getAllJobs() {
        return jobService.getAllJobs();
    }
}
