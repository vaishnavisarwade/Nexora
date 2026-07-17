package com.vaishnavi.nexora.document.dto;

import java.time.LocalDateTime;

public class DocumentResponse {

    private Long id;

    private String title;

    private String category;

    private String fileName;

    private String fileType;

    private String filePath;

    private String description;

    private LocalDateTime uploadedDate;

    public DocumentResponse() {
    }

    public DocumentResponse(Long id, String title, String category,
                            String fileName, String fileType,
                            String filePath, String description,
                            LocalDateTime uploadedDate) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.description = description;
        this.uploadedDate = uploadedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDateTime uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}