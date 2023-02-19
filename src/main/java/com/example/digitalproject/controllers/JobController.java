package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@AllArgsConstructor
public class JobController {
    private JobService jobService;

    @GetMapping("/job/{id}")
    public JobGetDTO getJob(@PathVariable Long id) {
        return jobService.getJob(id);
    }

    @PostMapping("/job/")
    public ResponseEntity<?> postJob(JobPostDTO JobPostDTO) {
        jobService.postJob(JobPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/job/")
    public ResponseEntity<?> putJob(JobPutDTO JobPutDTO) {
        jobService.putJob(JobPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/job/")
    public List<JobGetDTO> getAllJobs() {
        return jobService.getAllJobs();
    }
}
