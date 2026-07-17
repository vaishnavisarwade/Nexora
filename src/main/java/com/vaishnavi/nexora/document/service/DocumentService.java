package com.vaishnavi.nexora.document.service;


import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;
import com.vaishnavi.nexora.common.service.PaginationService;

import com.vaishnavi.nexora.document.dto.DocumentRequest;
import com.vaishnavi.nexora.document.dto.DocumentResponse;
import com.vaishnavi.nexora.document.entity.Document;
import com.vaishnavi.nexora.document.repository.DocumentRepository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.file.service.FileStorageService;
import com.vaishnavi.nexora.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class DocumentService {



    @Autowired
    private DocumentRepository documentRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    private PaginationService paginationService;





    private User getLoggedInUser(Authentication authentication) {

        return userRepository.findByEmail(authentication.getName())

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }







    // CREATE DOCUMENT

    public DocumentResponse createDocument(
            DocumentRequest request,
            MultipartFile file,
            Authentication authentication) {


        User user = getLoggedInUser(authentication);


        String storedFileName =
                fileStorageService.saveFile(file);



        Document document = new Document();


        document.setTitle(request.getTitle());

        document.setCategory(request.getCategory());

        document.setDescription(request.getDescription());


        document.setFileName(
                file.getOriginalFilename()
        );


        document.setFileType(
                file.getContentType()
        );


        document.setFilePath(
                storedFileName
        );


        document.setUploadedDate(
                LocalDateTime.now()
        );


        document.setUser(user);



        Document saved =
                documentRepository.save(document);



        return mapToResponse(saved);
    }









    // GET DOCUMENTS WITH PAGINATION + SEARCH + FILTER

    public PageResponse<DocumentResponse> getAllDocuments(
            PageRequestDTO request,
            Authentication authentication) {



        User user =
                getLoggedInUser(authentication);



        Pageable pageable =
                paginationService.createPageable(request);



        Page<Document> page;



        if(request.getKeyword() != null &&
                !request.getKeyword().isEmpty()) {



            page =
                    documentRepository
                            .findByUserAndTitleContainingIgnoreCase(
                                    user,
                                    request.getKeyword(),
                                    pageable
                            );


        }

        else {


            page =
                    documentRepository.findByUser(
                            user,
                            pageable
                    );
        }





        List<DocumentResponse> documents =
                page.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());




        return new PageResponse<>(
                documents,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }









    // FILTER BY CATEGORY

    public PageResponse<DocumentResponse> getDocumentsByCategory(
            String category,
            PageRequestDTO request,
            Authentication authentication) {



        User user =
                getLoggedInUser(authentication);



        Pageable pageable =
                paginationService.createPageable(request);



        Page<Document> page =
                documentRepository
                        .findByUserAndCategory(
                                user,
                                category,
                                pageable
                        );



        List<DocumentResponse> documents =
                page.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());



        return new PageResponse<>(
                documents,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }









    // GET DOCUMENT BY ID

    public DocumentResponse getDocumentById(
            Long id,
            Authentication authentication) {


        Document document =
                getDocumentEntity(
                        id,
                        authentication
                );


        return mapToResponse(document);
    }









    // UPDATE DOCUMENT

    public DocumentResponse updateDocument(
            Long id,
            DocumentRequest request,
            MultipartFile file,
            Authentication authentication) {


        Document document =
                getDocumentEntity(
                        id,
                        authentication
                );



        document.setTitle(
                request.getTitle()
        );


        document.setCategory(
                request.getCategory()
        );


        document.setDescription(
                request.getDescription()
        );




        if(file != null && !file.isEmpty()) {


            if(document.getFilePath()!=null){

                fileStorageService.deleteFile(
                        document.getFilePath()
                );
            }



            String storedFileName =
                    fileStorageService.saveFile(file);



            document.setFileName(
                    file.getOriginalFilename()
            );


            document.setFileType(
                    file.getContentType()
            );


            document.setFilePath(
                    storedFileName
            );

        }




        return mapToResponse(
                documentRepository.save(document)
        );

    }









    // DELETE DOCUMENT

    public void deleteDocument(
            Long id,
            Authentication authentication) {


        Document document =
                getDocumentEntity(
                        id,
                        authentication
                );



        if(document.getFilePath()!=null){

            fileStorageService.deleteFile(
                    document.getFilePath()
            );

        }


        documentRepository.delete(document);

    }









    // DOWNLOAD DOCUMENT

    public Resource downloadDocument(
            Long id,
            Authentication authentication) {


        Document document =
                getDocumentEntity(
                        id,
                        authentication
                );


        return fileStorageService.loadFile(
                document.getFilePath()
        );
    }









    // GET DOCUMENT ENTITY

    public Document getDocumentEntity(
            Long id,
            Authentication authentication) {


        User user =
                getLoggedInUser(authentication);



        Document document =
                documentRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Document not found"
                                ));




        if(!document.getUser()
                .getId()
                .equals(user.getId())) {


            throw new ResourceNotFoundException(
                    "Document not found"
            );

        }


        return document;

    }









    // ENTITY TO RESPONSE

    private DocumentResponse mapToResponse(
            Document document) {


        return new DocumentResponse(

                document.getId(),

                document.getTitle(),

                document.getCategory(),

                document.getFileName(),

                document.getFileType(),

                document.getFilePath(),

                document.getDescription(),

                document.getUploadedDate()

        );
    }

}