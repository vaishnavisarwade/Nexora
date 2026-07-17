package com.vaishnavi.nexora.controller;


import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;
import com.vaishnavi.nexora.dto.NoteRequest;
import com.vaishnavi.nexora.dto.NoteResponse;
import com.vaishnavi.nexora.service.NoteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notes")
@PreAuthorize("isAuthenticated()")
public class NoteController {


    @Autowired
    private NoteService noteService;



    // ================= CREATE NOTE =================

    @PostMapping
    public NoteResponse createNote(
            Authentication authentication,
            @Valid @RequestBody NoteRequest request
    ){

        return noteService.createNote(
                authentication.getName(),
                request
        );
    }



    // ================= GET MY NOTES =================

    @GetMapping
    public PageResponse<NoteResponse> getMyNotes(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String keyword
    ){

        PageRequestDTO request = new PageRequestDTO();

        request.setPage(page);
        request.setSize(size);
        request.setSortBy(sortBy);
        request.setDirection(direction);
        request.setKeyword(keyword);


        return noteService.getMyNotes(
                authentication.getName(),
                request
        );
    }



    @GetMapping("/{id}")
    public NoteResponse getNoteById(
            @PathVariable Long id
    ){

        return noteService.getNoteById(id);

    }



    @PutMapping("/{id}")
    public NoteResponse updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request
    ){

        return noteService.updateNote(
                id,
                request
        );

    }



    @DeleteMapping("/{id}")
    public String deleteNote(
            @PathVariable Long id
    ){

        noteService.deleteNote(id);

        return "Note moved to Trash successfully";

    }



    @PutMapping("/{id}/restore")
    public NoteResponse restoreNote(
            @PathVariable Long id
    ){

        return noteService.restoreNote(id);

    }



    @DeleteMapping("/{id}/permanent")
    public String deleteNotePermanently(
            @PathVariable Long id
    ){

        noteService.deleteNotePermanently(id);

        return "Note permanently deleted";

    }



    @GetMapping("/search")
    public List<NoteResponse> searchNotes(
            Authentication authentication,
            @RequestParam String keyword
    ){

        return noteService.searchNotes(
                authentication.getName(),
                keyword
        );

    }



    @GetMapping("/pinned")
    public List<NoteResponse> getPinnedNotes(
            Authentication authentication
    ){

        return noteService.getPinnedNotes(
                authentication.getName()
        );

    }



    @GetMapping("/archived")
    public List<NoteResponse> getArchivedNotes(
            Authentication authentication
    ){

        return noteService.getArchivedNotes(
                authentication.getName()
        );

    }



    @GetMapping("/favorites")
    public List<NoteResponse> getFavoriteNotes(
            Authentication authentication
    ){

        return noteService.getFavoriteNotes(
                authentication.getName()
        );

    }



    @GetMapping("/trash")
    public List<NoteResponse> getDeletedNotes(
            Authentication authentication
    ){

        return noteService.getDeletedNotes(
                authentication.getName()
        );

    }



    @GetMapping("/category/{categoryId}")
    public List<NoteResponse> getNotesByCategory(
            @PathVariable Long categoryId,
            Authentication authentication
    ){

        return noteService.getNotesByCategory(
                authentication.getName(),
                categoryId
        );

    }



    @PutMapping("/{id}/pin")
    public NoteResponse pinNote(
            @PathVariable Long id
    ){

        return noteService.pinNote(id);

    }



    @PutMapping("/{id}/unpin")
    public NoteResponse unpinNote(
            @PathVariable Long id
    ){

        return noteService.unpinNote(id);

    }



    @PutMapping("/{id}/archive")
    public NoteResponse archiveNote(
            @PathVariable Long id
    ){

        return noteService.archiveNote(id);

    }



    @PutMapping("/{id}/unarchive")
    public NoteResponse unarchiveNote(
            @PathVariable Long id
    ){

        return noteService.unarchiveNote(id);

    }



    @PutMapping("/{id}/favorite")
    public NoteResponse favoriteNote(
            @PathVariable Long id
    ){

        return noteService.favoriteNote(id);

    }



    @PutMapping("/{id}/unfavorite")
    public NoteResponse unfavoriteNote(
            @PathVariable Long id
    ){

        return noteService.unfavoriteNote(id);

    }

}