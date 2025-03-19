package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        System.out.println(songs);
        return songMapper.toDTOs(songs);
    }
}
