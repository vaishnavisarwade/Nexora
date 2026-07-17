package com.vaishnavi.nexora.document.controller;


import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;

import com.vaishnavi.nexora.document.dto.DocumentRequest;
import com.vaishnavi.nexora.document.dto.DocumentResponse;
import com.vaishnavi.nexora.document.entity.Document;
import com.vaishnavi.nexora.document.service.DocumentService;
import com.vaishnavi.nexora.file.service.FileStorageService;


import io.swagger.v3.oas.annotations.Operation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/documents")
public class DocumentController {



    @Autowired
    private DocumentService documentService;



    @Autowired
    private FileStorageService fileStorageService;





    // CREATE DOCUMENT

    @Operation(summary = "Upload document")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentResponse createDocument(

            @RequestParam String title,

            @RequestParam(required = false) String category,

            @RequestParam(required = false) String description,

            @RequestParam MultipartFile file,

            Authentication authentication) {



        DocumentRequest request =
                new DocumentRequest(
                        title,
                        category,
                        description
                );



        return documentService.createDocument(
                request,
                file,
                authentication
        );
    }








    // GET DOCUMENTS WITH PAGINATION SEARCH SORT

    @Operation(
            summary = "Get documents with pagination, search and sorting"
    )
    @GetMapping
    public PageResponse<DocumentResponse> getAllDocuments(

            @ModelAttribute PageRequestDTO request,

            Authentication authentication) {



        return documentService.getAllDocuments(
                request,
                authentication
        );
    }









    // GET DOCUMENT BY ID

    @Operation(summary = "Get document by id")
    @GetMapping("/{id}")
    public DocumentResponse getDocumentById(

            @PathVariable Long id,

            Authentication authentication) {



        return documentService.getDocumentById(
                id,
                authentication
        );
    }









    // UPDATE DOCUMENT

    @Operation(summary = "Update document")
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public DocumentResponse updateDocument(

            @PathVariable Long id,

            @RequestParam String title,

            @RequestParam(required = false) String category,

            @RequestParam(required = false) String description,

            @RequestParam(required = false) MultipartFile file,

            Authentication authentication) {



        DocumentRequest request =
                new DocumentRequest(
                        title,
                        category,
                        description
                );



        return documentService.updateDocument(
                id,
                request,
                file,
                authentication
        );
    }









    // DOWNLOAD DOCUMENT

    @Operation(summary = "Download document")
    @GetMapping(
            value = "/{id}/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> downloadDocument(

            @PathVariable Long id,

            Authentication authentication) {



        Resource resource =
                documentService.downloadDocument(
                        id,
                        authentication
                );



        return ResponseEntity.ok()

                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + resource.getFilename()
                                + "\""
                )

                .body(resource);
    }









    // VIEW DOCUMENT

    @Operation(summary = "View document in browser")
    @GetMapping(
            value = "/{id}/view",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> viewDocument(

            @PathVariable Long id,

            Authentication authentication) {



        Document document =
                documentService.getDocumentEntity(
                        id,
                        authentication
                );



        Resource resource =
                documentService.downloadDocument(
                        id,
                        authentication
                );



        String contentType =
                fileStorageService.getFileContentType(
                        document.getFilePath()
                );



        if(contentType == null){

            contentType =
                    MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }



        return ResponseEntity.ok()

                .contentType(
                        MediaType.parseMediaType(contentType)
                )

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\""
                                + document.getFileName()
                                + "\""
                )

                .body(resource);

    }









    // DELETE DOCUMENT

    @Operation(summary = "Delete document")
    @DeleteMapping("/{id}")
    public String deleteDocument(

            @PathVariable Long id,

            Authentication authentication) {



        documentService.deleteDocument(
                id,
                authentication
        );


        return "Document deleted successfully";
    }

}