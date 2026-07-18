package com.vaishnavi.nexora.document.service;

import com.vaishnavi.nexora.document.entity.Document;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentTextExtractionService {


    private final Tika tika = new Tika();


    @Value("${file.upload-dir}")
    private String uploadDir;


    public String extractText(
            Document document
    ) {

        try {

            Path filePath =
                    Paths.get(uploadDir)
                            .resolve(document.getFilePath())
                            .normalize();


            if (!Files.exists(filePath)) {

                return "Unable to extract text from document.";
            }


            return tika.parseToString(
                    filePath.toFile()
            );


        } catch (IOException e) {

            return "Unable to extract text from document.";

        } catch (Exception e) {

            return "Unable to extract text from document.";
        }
    }
}