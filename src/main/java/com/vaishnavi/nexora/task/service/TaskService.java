package com.vaishnavi.nexora.task.service;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.repository.UserRepository;
import com.vaishnavi.nexora.task.dto.TaskRequest;
import com.vaishnavi.nexora.task.dto.TaskResponse;
import com.vaishnavi.nexora.task.entity.Task;
import com.vaishnavi.nexora.task.entity.TaskPriority;
import com.vaishnavi.nexora.task.entity.TaskStatus;
import com.vaishnavi.nexora.task.repository.TaskRepository;

import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


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




    // Create Task
    public TaskResponse createTask(TaskRequest request) {


        Task task = new Task();


        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());


        if(request.getStatus() != null){

            task.setStatus(
                    TaskStatus.valueOf(request.getStatus())
            );

        }


        if(request.getPriority() != null){

            task.setPriority(
                    TaskPriority.valueOf(request.getPriority())
            );

        }



        task.setUser(getLoggedInUser());


        return mapToResponse(
                taskRepository.save(task)
        );

    }




    // Pagination + Sorting
    public Page<TaskResponse> getAllTasks(
            int page,
            int size,
            String sortBy,
            String direction
    ){


        User user = getLoggedInUser();


        Sort sort =
                direction.equalsIgnoreCase("asc")
                        ?
                        Sort.by(sortBy).ascending()
                        :
                        Sort.by(sortBy).descending();



        Pageable pageable =
                PageRequest.of(page,size,sort);



        return taskRepository
                .findByUser(user,pageable)
                .map(this::mapToResponse);

    }




    // Get Task By ID
    public TaskResponse getTaskById(Long id){


        User user=getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found")
                        );


        return mapToResponse(task);

    }





    // Update Task
    public TaskResponse updateTask(
            Long id,
            TaskRequest request
    ){


        User user=getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found")
                        );



        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setDueDate(request.getDueDate());



        if(request.getStatus()!=null){

            task.setStatus(
                    TaskStatus.valueOf(request.getStatus())
            );

        }



        if(request.getPriority()!=null){

            task.setPriority(
                    TaskPriority.valueOf(request.getPriority())
            );

        }



        return mapToResponse(
                taskRepository.save(task)
        );

    }




    // Delete Task
    public void deleteTask(Long id){


        User user=getLoggedInUser();


        Task task =
                taskRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found")
                        );


        taskRepository.delete(task);

    }





    // Search Tasks
    public Page<TaskResponse> searchTasks(
            String keyword,
            int page,
            int size
    ){


        User user=getLoggedInUser();


        Pageable pageable =
                PageRequest.of(page,size);



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





    // Filter By Status
    public Page<TaskResponse> getTasksByStatus(
            String status,
            int page,
            int size
    ){


        User user=getLoggedInUser();


        Pageable pageable =
                PageRequest.of(page,size);



        return taskRepository
                .findByUserAndStatus(
                        user,
                        TaskStatus.valueOf(status),
                        pageable
                )
                .map(this::mapToResponse);

    }





    // Filter By Priority
    public Page<TaskResponse> getTasksByPriority(
            String priority,
            int page,
            int size
    ){


        User user=getLoggedInUser();


        Pageable pageable =
                PageRequest.of(page,size);



        return taskRepository
                .findByUserAndPriority(
                        user,
                        TaskPriority.valueOf(priority),
                        pageable
                )
                .map(this::mapToResponse);

    }






    // JWT Logged User
    private User getLoggedInUser(){


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();



        String email =
                authentication.getName();



        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

    }





    // Entity -> DTO
    private TaskResponse mapToResponse(Task task){


        return new TaskResponse(

                task.getId(),

                task.getTitle(),

                task.getDescription(),

                task.getStatus().name(),

                task.getPriority().name(),

                task.getDueDate(),

                task.getCreatedAt(),

                task.getUpdatedAt()

        );

    }

}