package com.vaishnavi.nexora.controller;

import com.vaishnavi.nexora.dto.NoteRequest;
import com.vaishnavi.nexora.dto.NoteResponse;
import com.vaishnavi.nexora.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public NoteResponse createNote(
            Authentication authentication,
            @Valid @RequestBody NoteRequest request
    ) {

        return noteService.createNote(
                authentication.getName(),
                request
        );
    }

    @GetMapping
    public List<NoteResponse> getMyNotes(
            Authentication authentication
    ) {

        return noteService.getMyNotes(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(
            @PathVariable Long id
    ) {

        return noteService.getNoteById(id);
    }

    @PutMapping("/{id}")
    public NoteResponse updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request
    ) {

        return noteService.updateNote(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteNote(
            @PathVariable Long id
    ) {

        noteService.deleteNote(id);

        return "Note deleted successfully";
    }
}