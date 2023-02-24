package com.example.digitalproject.controllers;

import com.example.digitalproject.models.dto.tasks.TaskGetDTO;
import com.example.digitalproject.models.dto.tasks.TaskPostDTO;
import com.example.digitalproject.models.dto.tasks.TaskPutDTO;
import com.example.digitalproject.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/task/{id}")
    public TaskGetDTO getTask(@PathVariable Long id) {
        return taskService.getEntity(id);
    }

    @PostMapping("/task/")
    public ResponseEntity<?> postTask(@RequestBody TaskPostDTO TaskPostDTO) {
        taskService.postEntity(TaskPostDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/task/")
    public ResponseEntity<?> putTask(@RequestBody TaskPutDTO TaskPutDTO) {
        taskService.putEntity(TaskPutDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task/")
    public List<TaskGetDTO> getAllTasks() {
        return taskService.getAllEntities();
    }
}
