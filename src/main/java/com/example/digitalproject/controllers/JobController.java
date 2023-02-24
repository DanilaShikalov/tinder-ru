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
        return jobService.getEntity(id);
    }

    @PostMapping("/job/")
    public ResponseEntity<?> postJob(@RequestBody JobPostDTO jobPostDTO) {
        jobService.postEntity(jobPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/job/")
    public ResponseEntity<?> putJob(@RequestBody JobPutDTO jobPutDTO, @RequestParam Long id) {
        jobService.putEntity(jobPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/job/")
    public List<JobGetDTO> getAllJobs() {
        return jobService.getAllEntities();
    }
}
