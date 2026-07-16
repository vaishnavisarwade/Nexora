package com.vaishnavi.nexora.repository;

import com.vaishnavi.nexora.entity.Note;
import com.vaishnavi.nexora.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

}