package com.vaishnavi.nexora.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // =========================
    // Upload File
    // =========================
    public String saveFile(MultipartFile file) {

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();

            String fileExtension = "";

            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension =
                        originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            String uniqueFileName =
                    UUID.randomUUID().toString() + fileExtension;

            Path filePath = uploadPath.resolve(uniqueFileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return uniqueFileName;

        } catch (IOException e) {

            throw new RuntimeException("Failed to store file.", e);

        }

    }

    // =========================
    // Delete File
    // =========================
    public void deleteFile(String fileName) {

        try {

            Path filePath = Paths.get(uploadDir).resolve(fileName);

            Files.deleteIfExists(filePath);

        } catch (IOException e) {

            throw new RuntimeException("Failed to delete file.", e);

        }

    }

    // =========================
    // Load File
    // =========================
    public Resource loadFile(String fileName) {

        try {

            Path filePath = Paths.get(uploadDir)
                    .resolve(fileName)
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            }

            throw new RuntimeException("File not found.");

        } catch (MalformedURLException e) {

            throw new RuntimeException("File not found.", e);

        }

    }

    // =========================
    // Detect File Content Type
    // =========================
    public String getFileContentType(String fileName) {

        try {

            Path filePath = Paths.get(uploadDir)
                    .resolve(fileName)
                    .normalize();

            String contentType = Files.probeContentType(filePath);

            if (contentType == null) {
                return "application/octet-stream";
            }

            return contentType;

        } catch (IOException e) {

            return "application/octet-stream";

        }

    }

}