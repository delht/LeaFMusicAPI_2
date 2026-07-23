package de.lht.leafmusic3.storage.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import de.lht.leafmusic3.storage.constant.StorageFolder;
import de.lht.leafmusic3.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryStorageService implements StorageService {

    private final Cloudinary cloudinary;
    StorageFolder storageFolder;

    @Override
    public String upload(MultipartFile file, String folder) throws IOException {

        assert file.getOriginalFilename() != null;

        String publicId = generatePublicId(file.getOriginalFilename());

        String extension = getFileName(file.getOriginalFilename())[1];

        File fileUpload = convert(file);

        cloudinary.uploader().upload(
                fileUpload,
                ObjectUtils.asMap(
                        "public_id", publicId,
                        "folder", folder,
                        "resource_type", "raw"
                )
        );

        cleanDisk(fileUpload);

        String fileUrl = cloudinary.url()
                .resourceType("raw")
                .generate(folder + publicId + "." + extension);

        log.info("Upload thành công: {}", fileUrl);

        return fileUrl;
    }

    @Override
    public void delete(String fileUrl, String folder) throws IOException {

        String publicId = extractPublicId(fileUrl, folder);

        log.info("Xóa file: {}", publicId);

        Map response = cloudinary.uploader().destroy(
                publicId,
                ObjectUtils.asMap("resource_type", "raw")
        );

        log.info("Cloudinary response: {}", response);

        if (!"ok".equals(response.get("result"))) {
            throw new RuntimeException(
                    "Không thể xóa file Cloudinary: " + response
            );
        }
    }

    /**
     * Từ URL lấy ra publicId
     */

    private String extractPublicId(String fileUrl, String folder) {

//        String folder = storageFolder.ALBUM;

        int start = fileUrl.indexOf(folder);

        if (start == -1) {
            throw new IllegalArgumentException("Không tìm thấy folder trong URL: " + fileUrl);
        }

        return fileUrl.substring(start);
    }

    /**
     * Sinh public id
     */
    private String generatePublicId(String originalName) {

        String fileName = getFileName(originalName)[0];

        return UUID.randomUUID() + "_" + fileName;
    }

    /**
     * [abc, mp3]
     */
    private String[] getFileName(String originalName) {

        return originalName.split("\\.");
    }

    /**
     * MultipartFile -> File
     */
    private File convert(MultipartFile file)
            throws IOException {

        File convFile = new File(
                StringUtils.join(
                        generatePublicId(file.getOriginalFilename()),
                        ".",
                        getFileName(file.getOriginalFilename())[1]
                )
        );

        try (InputStream is = file.getInputStream()) {

            Files.copy(is, convFile.toPath());

        }

        return convFile;
    }

    /**
     * Xóa file tạm
     */
    private void cleanDisk(File file) {

        try {

            Path path = file.toPath();

            Files.delete(path);

        } catch (IOException e) {

            log.error("Không thể xóa file tạm.");

        }

    }

}