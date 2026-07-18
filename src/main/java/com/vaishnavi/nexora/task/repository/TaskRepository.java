package com.vaishnavi.nexora.task.repository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.task.entity.Task;
import com.vaishnavi.nexora.task.entity.TaskPriority;
import com.vaishnavi.nexora.task.entity.TaskStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TaskRepository
        extends JpaRepository<Task, Long> {


    // Pagination + all user tasks
    Page<Task> findByUser(
            User user,
            Pageable pageable
    );


    // Get single task of specific user
    Optional<Task> findByIdAndUser(
            Long id,
            User user
    );


    // Search by title or description
    Page<Task>
    findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
            User user1,
            String titleKeyword,
            User user2,
            String descriptionKeyword,
            Pageable pageable
    );


    // Filter by status
    Page<Task> findByUserAndStatus(
            User user,
            TaskStatus status,
            Pageable pageable
    );


    // Filter by priority
    Page<Task> findByUserAndPriority(
            User user,
            TaskPriority priority,
            Pageable pageable
    );


    // Search + status filter
    Page<Task> findByUserAndStatusAndTitleContainingIgnoreCase(
            User user,
            TaskStatus status,
            String keyword,
            Pageable pageable
    );


    // Central AI - get user's tasks ordered by due date
    Page<Task> findByUserOrderByDueDateAsc(
            User user,
            Pageable pageable
    );


    // Central AI - get user's recent tasks
    Page<Task> findByUserOrderByCreatedAtDesc(
            User user,
            Pageable pageable
    );

}