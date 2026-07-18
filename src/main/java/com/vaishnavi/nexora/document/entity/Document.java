package com.vaishnavi.nexora.document.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "documents",

        indexes = {

                // Frequently used for user's documents
                @Index(
                        name = "idx_documents_user_id",
                        columnList = "user_id"
                ),

                // Search optimization
                @Index(
                        name = "idx_documents_title",
                        columnList = "title"
                ),

                // Category filtering
                @Index(
                        name = "idx_documents_category",
                        columnList = "category"
                ),

                // File type filtering
                @Index(
                        name = "idx_documents_file_type",
                        columnList = "file_type"
                ),

                // Sorting by upload date
                @Index(
                        name = "idx_documents_uploaded_date",
                        columnList = "uploaded_date"
                )
        }
)
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    private String category;


    // Original uploaded file name
    private String fileName;


    // MIME type
    private String fileType;


    // Stored unique file name/path
    private String filePath;


    private String description;


    private LocalDateTime uploadedDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public Document() {

    }


    public Document(
            Long id,
            String title,
            String category,
            String fileName,
            String fileType,
            String filePath,
            String description,
            LocalDateTime uploadedDate,
            User user
    ) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.description = description;
        this.uploadedDate = uploadedDate;
        this.user = user;
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}