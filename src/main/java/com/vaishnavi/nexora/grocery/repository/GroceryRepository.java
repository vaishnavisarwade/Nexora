package com.vaishnavi.nexora.grocery.repository;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.grocery.entity.Grocery;
import com.vaishnavi.nexora.grocery.entity.GroceryStatus;
import com.vaishnavi.nexora.grocery.entity.Priority;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;



@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {



    // ================= PAGINATION =================

    Page<Grocery> findByUser(
            User user,
            Pageable pageable
    );






    // ================= FIND SINGLE USER GROCERY =================

    Optional<Grocery> findByIdAndUser(
            Long id,
            User user
    );






    // ================= SEARCH ITEM NAME =================

    Page<Grocery> findByUserAndItemNameContainingIgnoreCase(
            User user,
            String keyword,
            Pageable pageable
    );






    // ================= SEARCH ITEM + CATEGORY =================

    Page<Grocery> findByUserAndItemNameContainingIgnoreCaseOrUserAndCategoryContainingIgnoreCase(
            User user1,
            String itemKeyword,
            User user2,
            String categoryKeyword,
            Pageable pageable
    );






    // ================= CATEGORY FILTER =================

    Page<Grocery> findByUserAndCategory(
            User user,
            String category,
            Pageable pageable
    );






    // ================= STATUS FILTER =================

    Page<Grocery> findByUserAndStatus(
            User user,
            GroceryStatus status,
            Pageable pageable
    );






    // ================= PRIORITY FILTER =================

    Page<Grocery> findByUserAndPriority(
            User user,
            Priority priority,
            Pageable pageable
    );


}