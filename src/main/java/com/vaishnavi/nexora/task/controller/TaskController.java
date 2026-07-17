package com.vaishnavi.nexora.task.controller;


import com.vaishnavi.nexora.task.dto.TaskRequest;
import com.vaishnavi.nexora.task.dto.TaskResponse;
import com.vaishnavi.nexora.task.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/tasks")
public class TaskController {


    private final TaskService taskService;



    public TaskController(TaskService taskService) {

        this.taskService = taskService;

    }





    // Create Task
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request
    ){

        return ResponseEntity.ok(
                taskService.createTask(request)
        );

    }





    // Get All Tasks With Pagination + Sorting
    // Example:
    // /tasks?page=0&size=10&sortBy=createdAt&direction=desc

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "createdAt") String sortBy,

            @RequestParam(defaultValue = "desc") String direction

    ){

        return ResponseEntity.ok(
                taskService.getAllTasks(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );

    }





    // Get Task By ID

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(

            @PathVariable Long id

    ){

        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );

    }





    // Update Task

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(

            @PathVariable Long id,

            @Valid @RequestBody TaskRequest request

    ){

        return ResponseEntity.ok(
                taskService.updateTask(id,request)
        );

    }





    // Search Task
    // Example:
    // /tasks/search?keyword=project&page=0&size=10

    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponse>> searchTasks(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){

        return ResponseEntity.ok(
                taskService.searchTasks(
                        keyword,
                        page,
                        size
                )
        );

    }





    // Filter By Status
    // Example:
    // /tasks/status/PENDING

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<TaskResponse>> getTasksByStatus(

            @PathVariable String status,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){

        return ResponseEntity.ok(
                taskService.getTasksByStatus(
                        status,
                        page,
                        size
                )
        );

    }





    // Filter By Priority
    // Example:
    // /tasks/priority/HIGH

    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<TaskResponse>> getTasksByPriority(

            @PathVariable String priority,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){

        return ResponseEntity.ok(
                taskService.getTasksByPriority(
                        priority,
                        page,
                        size
                )
        );

    }





    // Delete Task

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(

            @PathVariable Long id

    ){

        taskService.deleteTask(id);


        return ResponseEntity.ok(
                "Task deleted successfully"
        );

    }

}