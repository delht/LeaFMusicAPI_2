package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.album.AlbumRequest;
import de.lht.leafmusic3.dto.album.AlbumRespone;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.exceptions.AppException;
import de.lht.leafmusic3.mapper.AlbumMapper;
import de.lht.leafmusic3.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    private Album findAlbumById(String id) {
        try {
            Integer albumId = Integer.parseInt(id);

            return albumRepository.findById(albumId)
                    .orElseThrow(() ->
                            new AppException(
                                    HttpStatus.NOT_FOUND,
                                    "Không tìm thấy album"));
        } catch (NumberFormatException e) {
            throw new AppException(
                    HttpStatus.NOT_FOUND,
                    "Không tìm thấy album");
        }
    }

    //===========================================

    public List<AlbumRespone> getAllAlbums() {
        return albumMapper.toDtoList(albumRepository.findAll());
    }

//    public AlbumRespone getAlbumById(int id){
//        Album album = albumRepository.findById(id)
//                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy album"));
//        return albumMapper.toDto(album);
//    }

    public AlbumRespone getAlbumById(String id) {
        return albumMapper.toDto(findAlbumById(id));
    }

    public AlbumRespone createAlbum(AlbumRequest request){
        Album album = albumMapper.toEntity(request);
        album.setCreatedAt(LocalDateTime.now());
        return albumMapper.toDto(albumRepository.save(album));
    }

    public AlbumRespone updateAlbum(String id, AlbumRequest request) {
        Album album = findAlbumById(id);

        album.setName(request.getName());
        album.setImageUrl(request.getImageUrl());
        album.setReleaseDate(request.getReleaseDate());
        album.setIdArtist(request.getIdArtist());
        album.setUploadBy(request.getUploadBy());

        return albumMapper.toDto(albumRepository.save(album));
    }

    public void deleteAlbum(String id) {
        Album album = findAlbumById(id);
        albumRepository.delete(album);
    }


}
