package de.lht.leafmusic3.cloud.repo;

import java.io.IOException;

public interface DeleteFile {
    void deleteFile(String publicId) throws IOException;
}
