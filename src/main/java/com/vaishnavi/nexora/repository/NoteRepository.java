package com.vaishnavi.nexora.repository;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.expense.entity.Note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {


    // ================= FIND NOTE BY ID AND USER =================

    Optional<Note> findByIdAndUser(
            Long id,
            User user
    );


    // ================= PAGINATION =================

    Page<Note> findByUser(
            User user,
            Pageable pageable
    );


    // ================= SEARCH WITH PAGINATION =================

    Page<Note> findByUserAndTitleContainingIgnoreCase(
            User user,
            String keyword,
            Pageable pageable
    );


    // ================= SEARCH WITHOUT PAGINATION =================

    List<Note> findByUserAndTitleContainingIgnoreCase(
            User user,
            String keyword
    );


    // ================= CATEGORY FILTER =================

    Page<Note> findByUserAndCategoryId(
            User user,
            Long categoryId,
            Pageable pageable
    );


    List<Note> findByUserAndCategoryId(
            User user,
            Long categoryId
    );


    // ================= PINNED NOTES =================

    Page<Note> findByUserAndPinnedTrue(
            User user,
            Pageable pageable
    );


    List<Note> findByUserAndPinnedTrue(
            User user
    );


    // ================= ARCHIVED NOTES =================

    Page<Note> findByUserAndArchivedTrue(
            User user,
            Pageable pageable
    );


    List<Note> findByUserAndArchivedTrue(
            User user
    );


    // ================= FAVORITE NOTES =================

    Page<Note> findByUserAndFavoriteTrue(
            User user,
            Pageable pageable
    );


    List<Note> findByUserAndFavoriteTrue(
            User user
    );


    // ================= DELETED NOTES =================

    Page<Note> findByUserAndDeletedTrue(
            User user,
            Pageable pageable
    );


    List<Note> findByUserAndDeletedTrue(
            User user
    );


    // ================= DASHBOARD =================

    long countByUser(User user);


    long countByUserAndFavoriteTrue(
            User user
    );

}