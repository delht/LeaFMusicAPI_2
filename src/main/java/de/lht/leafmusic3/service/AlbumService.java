package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.exception.AppException;
import de.lht.leafmusic3.mapper.AlbumMapper;
import de.lht.leafmusic3.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.lht.leafmusic3.storage.constant.StorageFolder;
import de.lht.leafmusic3.storage.service.StorageService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;


    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albumMapper.toDTOs(albums);
    }

    public List<Album2DTO> getAllAlbums2() {
        List<Album2DTO> albums = albumRepository.findAllAlbum();
        return albums;
    }

    public List<AlbumDTO> getAlbumsByArtist(int artistId) {
        List<Album> albums = albumRepository.findByIdArtist(artistId)
                .orElseThrow(()->new AppException(HttpStatus.CONFLICT, "Không tìm thấy album của nghệ sĩ có id: "+artistId));
        return albumMapper.toDTOs(albums);
    }

    //TODO sửa random
    public List<AlbumDTO> getRandomAlbums(int limit){
        List<Album> albums = albumRepository.findRandomAlbums(limit);
        return albumMapper.toDTOs(albums);
    }

    public AlbumDTO getAlbumById(int albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AppException(HttpStatus.CONFLICT, "Không tìm thấy album có id: " + albumId));;
        return albumMapper.toDTO(album);
    }



//    =================================================================================

    private final StorageService storageService;


    @Transactional
    public Album addAlbum(MultipartFile img, AlbumRequestDTO albumRequestDTO) {

        try {

            String fileUrlImg = storageService.upload(img, StorageFolder.ALBUM);

            Album album = new Album();
            album.setName(albumRequestDTO.getName());
            album.setReleaseDate(albumRequestDTO.getReleaseDate());
            album.setIdArtist(albumRequestDTO.getIdArtist());
            album.setImageUrl(fileUrlImg);
            album.setUploadBy(albumRequestDTO.getUploadBy());

            return albumRepository.save(album);

        } catch (IOException e) {
            throw new AppException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload ảnh thất bại."
            );
        }
    }

    public void deleteAlbum(int id) throws IOException {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay album co id: "+id));

        log.info("Url file cần xóa: {}", album.getImageUrl());

        storageService.delete(album.getImageUrl());

        albumRepository.delete(album);

        log.info("Album với ID {} đã được xóa thành công.", id);
    }


    @Transactional
    public Album updateAlbum(int id, MultipartFile img, AlbumRequestDTO albumRequestDTO) throws IOException {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy album với id: " + id));

        // Cập nhật thông tin text
        album.setName(albumRequestDTO.getName());
        album.setReleaseDate(albumRequestDTO.getReleaseDate());
        album.setIdArtist(albumRequestDTO.getIdArtist());
        album.setUploadBy(album.getUploadBy());

        // Nếu có ảnh mới thì xử lý upload và xoá ảnh cũ
        if (img != null && !img.isEmpty()) {
            String oldImageUrl = album.getImageUrl();

            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {

                storageService.delete(oldImageUrl);

                log.info("Đã xóa ảnh cũ.");
            }

            String newImageUrl = storageService.upload(
                    img,
                    StorageFolder.ALBUM
            );

            album.setImageUrl(newImageUrl);

            log.info("Đã upload ảnh mới: {}", newImageUrl);
        } else {
            log.info("Không có ảnh mới, giữ nguyên ảnh cũ.");
        }

        return albumRepository.save(album);
    }


//    ===============================================================================================

        public List<AlbumDTO> getAlbumbyUser(String idUser){
            List<Album> albums = albumRepository.findByUploadBy(idUser);
            return albumMapper.toDTOs(albums);
        }









}
