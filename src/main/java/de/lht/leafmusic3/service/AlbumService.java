package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.mapper.AlbumMapper;
import de.lht.leafmusic3.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

//    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper) {
//        this.albumRepository = albumRepository;
//        this.albumMapper = albumMapper;
//    }

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        System.out.println("Dữ liệu album" + albums);
        return albumMapper.toDTOs(albums);
    }

}
