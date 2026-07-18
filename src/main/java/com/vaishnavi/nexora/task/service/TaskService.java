package com.vaishnavi.nexora.task.service;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.repository.UserRepository;
import com.vaishnavi.nexora.task.dto.TaskRequest;
import com.vaishnavi.nexora.task.dto.TaskResponse;
import com.vaishnavi.nexora.task.entity.Task;
import com.vaishnavi.nexora.task.entity.TaskPriority;
import com.vaishnavi.nexora.task.entity.TaskStatus;
import com.vaishnavi.nexora.task.repository.TaskRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {


    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    // =====================================================
    // CREATE TASK
    // =====================================================

    public TaskResponse createTask(TaskRequest request) {

        User user = getLoggedInUser();

        Task task = new Task();

        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setDueDate(request.getDueDate());


        if (request.getStatus() != null) {

            task.setStatus(
                    TaskStatus.valueOf(
                            request.getStatus()
                    )
            );
        }


        if (request.getPriority() != null) {

            task.setPriority(
                    TaskPriority.valueOf(
                            request.getPriority()
                    )
            );
        }


        task.setUser(user);


        return mapToResponse(
                taskRepository.save(task)
        );
    }


    // =====================================================
    // GET ALL TASKS
    // =====================================================

    public Page<TaskResponse> getAllTasks(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        User user = getLoggedInUser();


        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "createdAt";
        }


        if (direction == null || direction.isBlank()) {
            direction = "desc";
        }


        Sort sort =
                direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();


        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );


        return taskRepository
                .findByUser(user, pageable)
                .map(this::mapToResponse);
    }


    // =====================================================
    // CENTRAL AI - GET USER TASKS
    // =====================================================

    public List<Task> getTasksForAI() {

        User user = getLoggedInUser();


        Pageable pageable =
                PageRequest.of(
                        0,
                        50,
                        Sort.by(
                                "dueDate"
                        ).ascending()
                );


        return taskRepository
                .findByUser(
                        user,
                        pageable
                )
                .getContent();
    }


    // =====================================================
    // GET TASK BY ID
    // =====================================================

    public TaskResponse getTaskById(Long id) {

        User user = getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Task not found"
                                )
                        );


        return mapToResponse(task);
    }


    // =====================================================
    // UPDATE TASK
    // =====================================================

    public TaskResponse updateTask(
            Long id,
            TaskRequest request
    ) {

        User user = getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Task not found"
                                )
                        );


        task.setTitle(
                request.getTitle()
        );


        task.setDescription(
                request.getDescription()
        );


        task.setDueDate(
                request.getDueDate()
        );


        if (request.getStatus() != null) {

            task.setStatus(
                    TaskStatus.valueOf(
                            request.getStatus()
                    )
            );
        }


        if (request.getPriority() != null) {

            task.setPriority(
                    TaskPriority.valueOf(
                            request.getPriority()
                    )
            );
        }


        return mapToResponse(
                taskRepository.save(task)
        );
    }


    // =====================================================
    // DELETE TASK
    // =====================================================

    public void deleteTask(Long id) {

        User user = getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Task not found"
                                )
                        );


        taskRepository.delete(task);
    }


    // =====================================================
    // SEARCH TASKS
    // =====================================================

    public Page<TaskResponse> searchTasks(
            String keyword,
            int page,
            int size
    ) {

        User user = getLoggedInUser();


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return taskRepository
                .findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
                        user,
                        keyword,
                        user,
                        keyword,
                        pageable
                )
                .map(this::mapToResponse);
    }


    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public Page<TaskResponse> getTasksByStatus(
            String status,
            int page,
            int size
    ) {

        User user = getLoggedInUser();


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return taskRepository
                .findByUserAndStatus(
                        user,
                        TaskStatus.valueOf(status),
                        pageable
                )
                .map(this::mapToResponse);
    }


    // =====================================================
    // FILTER BY PRIORITY
    // =====================================================

    public Page<TaskResponse> getTasksByPriority(
            String priority,
            int page,
            int size
    ) {

        User user = getLoggedInUser();


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return taskRepository
                .findByUserAndPriority(
                        user,
                        TaskPriority.valueOf(priority),
                        pageable
                )
                .map(this::mapToResponse);
    }


    // =====================================================
    // GET LOGGED-IN USER
    // =====================================================

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        String email =
                authentication.getName();


        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );
    }


    // =====================================================
    // ENTITY → RESPONSE DTO
    // =====================================================

    private TaskResponse mapToResponse(Task task) {

        return new TaskResponse(

                task.getId(),

                task.getTitle(),

                task.getDescription(),

                task.getStatus() != null
                        ? task.getStatus().name()
                        : null,

                task.getPriority() != null
                        ? task.getPriority().name()
                        : null,

                task.getDueDate(),

                task.getCreatedAt(),

                task.getUpdatedAt()
        );
    }

}