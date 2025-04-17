package de.lht.leafmusic3.cloud.repo;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFile {
    String uploadFile(MultipartFile file, String foldername) throws IOException;
}
