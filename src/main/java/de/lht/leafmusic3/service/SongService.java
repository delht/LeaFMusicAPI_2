package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.ArtistRepository;
import de.lht.leafmusic3.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;
//    private final Random random = new Random();
    private final ArtistRepository artistRepository;

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songMapper.toDTOs(songs);
    }

    public SongDTO getSongById(int id) {
        Song song = songRepository.findById(id);
        return songMapper.toDTO(song);
    }

    //Lấy danh sách bài hát ngẫu nhiên (5 hoặc 10 bài)
    public List<SongDTO> getRandomSongs(int limit) {
        List<Song> songs = songRepository.findRandomSongs(limit);
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByArtist(int artistId) {
        List<Song> songs = songRepository.findByIdArtist(artistId);
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByAlbum(int albumId) {
        List<Song> songs = songRepository.findByIdAlbum(albumId);
        return songMapper.toDTOs(songs);
    }

}
