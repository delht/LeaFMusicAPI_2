package de.lht.leafmusic3.storage.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload (MultipartFile file, String folder) throws IOException;
    void delete (String fileUrl, String folder) throws IOException;;
}
