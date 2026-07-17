package com.vaishnavi.nexora.document.repository;


import com.vaishnavi.nexora.document.entity.Document;
import com.vaishnavi.nexora.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {



    // Get user documents with pagination

    Page<Document> findByUser(
            User user,
            Pageable pageable
    );



    // Filter by category with pagination

    Page<Document> findByUserAndCategory(
            User user,
            String category,
            Pageable pageable
    );



    // Search by title with pagination

    Page<Document> findByUserAndTitleContainingIgnoreCase(
            User user,
            String title,
            Pageable pageable
    );


}