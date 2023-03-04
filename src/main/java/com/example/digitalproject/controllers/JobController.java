package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@AllArgsConstructor
@SecurityRequirement(name = "digital-project")
@Tag(name = "Job", description = "Контроллер позиций работников")
public class JobController {
    private JobService jobService;

    @GetMapping("/job/{id}")
    @Operation(description = "Получить позицию по её id")
    public JobGetDTO getJob(@PathVariable Long id) {
        return jobService.getEntity(id);
    }

    @PostMapping("/job/")
    @Operation(description = "Добавить новую вакансию")
    public ResponseEntity<?> postJob(@RequestBody JobPostDTO jobPostDTO) {
        jobService.postEntity(jobPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/job/{id}")
    @Operation(description = "Удалить позицию по её id")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/job/")
    @Operation(description = "Изменить позицию по её id")
    public ResponseEntity<?> putJob(@RequestBody JobPutDTO jobPutDTO, @RequestParam Long id) {
        jobService.putEntity(jobPutDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/job/")
    @Operation(description = "Получить все позиции")
    public List<JobGetDTO> getAllJobs() {
        return jobService.getAllEntities();
    }
}
