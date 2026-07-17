package com.vaishnavi.nexora.service;


import com.vaishnavi.nexora.category.dto.CategoryResponse;
import com.vaishnavi.nexora.category.entity.Category;
import com.vaishnavi.nexora.category.repository.CategoryRepository;

import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;
import com.vaishnavi.nexora.common.service.PaginationService;

import com.vaishnavi.nexora.dto.NoteRequest;
import com.vaishnavi.nexora.dto.NoteResponse;

import com.vaishnavi.nexora.entity.User;

import com.vaishnavi.nexora.exception.ResourceNotFoundException;

import com.vaishnavi.nexora.expense.entity.Note;

import com.vaishnavi.nexora.repository.NoteRepository;
import com.vaishnavi.nexora.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;



@Service
public class NoteService {



    @Autowired
    private NoteRepository noteRepository;



    @Autowired
    private UserRepository userRepository;



    @Autowired
    private CategoryRepository categoryRepository;



    @Autowired
    private PaginationService paginationService;





    // ================= CREATE NOTE =================


    public NoteResponse createNote(
            String email,
            NoteRequest request
    ){


        User user = getUser(email);



        Note note = new Note();


        note.setTitle(request.getTitle());

        note.setContent(request.getContent());

        note.setColor(request.getColor());

        note.setUser(user);



        if(request.getCategoryId()!=null){


            Category category =
                    categoryRepository.findById(
                                    request.getCategoryId()
                            )
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Category not found"
                                    ));


            note.setCategory(category);

        }



        return convertToResponse(
                noteRepository.save(note)
        );

    }






    // ================= PAGINATION + SEARCH =================


    public PageResponse<NoteResponse> getMyNotes(
            String email,
            PageRequestDTO request
    ){


        User user = getUser(email);



        Pageable pageable =
                paginationService.createPageable(request);



        Page<Note> notes;



        if(request.getKeyword()!=null &&
                !request.getKeyword().isEmpty()) {


            notes =
                    noteRepository
                            .findByUserAndTitleContainingIgnoreCase(
                                    user,
                                    request.getKeyword()
                            )
                            .stream()
                            .map(note -> note)
                            .collect(Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    list -> new org.springframework.data.domain.PageImpl<>(
                                            list,
                                            pageable,
                                            list.size()
                                    )
                            ));


        } else {


            notes =
                    noteRepository.findByUser(
                            user,
                            pageable
                    );

        }



        List<NoteResponse> content =
                notes.getContent()
                        .stream()
                        .map(this::convertToResponse)
                        .collect(Collectors.toList());




        return new PageResponse<>(

                content,

                notes.getNumber(),

                notes.getSize(),

                notes.getTotalElements(),

                notes.getTotalPages()

        );

    }









    // ================= GET BY ID =================


    public NoteResponse getNoteById(Long id){

        return convertToResponse(
                findById(id)
        );

    }








    // ================= UPDATE =================


    public NoteResponse updateNote(
            Long id,
            NoteRequest request
    ){


        Note note = findById(id);



        note.setTitle(request.getTitle());

        note.setContent(request.getContent());

        note.setColor(request.getColor());



        if(request.getCategoryId()!=null){


            Category category =
                    categoryRepository.findById(
                                    request.getCategoryId()
                            )
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Category not found"
                                    ));


            note.setCategory(category);

        }



        return convertToResponse(
                noteRepository.save(note)
        );

    }








    // ================= DELETE =================


    public void deleteNote(Long id){


        Note note=findById(id);


        note.setDeleted(true);


        noteRepository.save(note);

    }







    // ================= RESTORE =================


    public NoteResponse restoreNote(Long id){


        Note note=findById(id);


        note.setDeleted(false);


        return convertToResponse(
                noteRepository.save(note)
        );

    }







    // ================= PERMANENT DELETE =================


    public void deleteNotePermanently(Long id){

        noteRepository.delete(
                findById(id)
        );

    }








    // ================= SEARCH =================


    public List<NoteResponse> searchNotes(
            String email,
            String keyword
    ){


        User user=getUser(email);



        return noteRepository
                .findByUserAndTitleContainingIgnoreCase(
                        user,
                        keyword
                )
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }








    // ================= PIN =================


    public NoteResponse pinNote(Long id){

        Note note=findById(id);

        note.setPinned(true);


        return convertToResponse(
                noteRepository.save(note)
        );

    }



    public NoteResponse unpinNote(Long id){

        Note note=findById(id);

        note.setPinned(false);


        return convertToResponse(
                noteRepository.save(note)
        );

    }








    // ================= ARCHIVE =================


    public NoteResponse archiveNote(Long id){

        Note note=findById(id);

        note.setArchived(true);


        return convertToResponse(
                noteRepository.save(note)
        );

    }




    public NoteResponse unarchiveNote(Long id){

        Note note=findById(id);

        note.setArchived(false);


        return convertToResponse(
                noteRepository.save(note)
        );

    }








    // ================= FAVORITE =================


    public NoteResponse favoriteNote(Long id){

        Note note=findById(id);

        note.setFavorite(true);


        return convertToResponse(
                noteRepository.save(note)
        );

    }



    public NoteResponse unfavoriteNote(Long id){

        Note note=findById(id);

        note.setFavorite(false);


        return convertToResponse(
                noteRepository.save(note)
        );

    }








    // ================= FILTERS =================


    public List<NoteResponse> getPinnedNotes(String email){

        User user=getUser(email);


        return noteRepository
                .findByUserAndPinnedTrue(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }




    public List<NoteResponse> getArchivedNotes(String email){

        User user=getUser(email);


        return noteRepository
                .findByUserAndArchivedTrue(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }





    public List<NoteResponse> getFavoriteNotes(String email){

        User user=getUser(email);


        return noteRepository
                .findByUserAndFavoriteTrue(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }







    public List<NoteResponse> getDeletedNotes(String email){

        User user=getUser(email);


        return noteRepository
                .findByUserAndDeletedTrue(user)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }








    public List<NoteResponse> getNotesByCategory(
            String email,
            Long categoryId
    ){


        User user=getUser(email);



        return noteRepository
                .findByUserAndCategoryId(
                        user,
                        categoryId
                )
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }








    // ================= HELPERS =================


    private Note findById(Long id){


        return noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found"
                        ));

    }





    private User getUser(String email){


        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

    }








    // ================= ENTITY TO DTO =================


    private NoteResponse convertToResponse(
            Note note
    ){


        NoteResponse response =
                new NoteResponse();


        response.setId(note.getId());

        response.setTitle(note.getTitle());

        response.setContent(note.getContent());

        response.setColor(note.getColor());



        if(note.getCategory()!=null){


            response.setCategory(
                    new CategoryResponse(
                            note.getCategory().getId(),
                            note.getCategory().getName()
                    )
            );

        }


        return response;

    }


}