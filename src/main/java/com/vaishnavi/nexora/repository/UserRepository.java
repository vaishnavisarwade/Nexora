package com.vaishnavi.nexora.repository;
import com.vaishnavi.nexora.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
