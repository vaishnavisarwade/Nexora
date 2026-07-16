package com.vaishnavi.nexora.service;

import com.vaishnavi.nexora.dto.NoteRequest;
import com.vaishnavi.nexora.dto.NoteResponse;
import com.vaishnavi.nexora.entity.Note;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.repository.NoteRepository;
import com.vaishnavi.nexora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


    public NoteResponse createNote(String email, NoteRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setUser(user);

        Note savedNote = noteRepository.save(note);

        return convertToResponse(savedNote);
    }


    public List<NoteResponse> getMyNotes(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return noteRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    public NoteResponse getNoteById(Long id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found"));

        return convertToResponse(note);
    }


    public NoteResponse updateNote(Long id, NoteRequest request) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found"));

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        Note updatedNote = noteRepository.save(note);

        return convertToResponse(updatedNote);
    }


    public void deleteNote(Long id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found"));

        noteRepository.delete(note);
    }


    private NoteResponse convertToResponse(Note note) {

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }
}