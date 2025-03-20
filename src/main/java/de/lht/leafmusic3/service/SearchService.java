package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.khac.SearchResultDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.mapper.AlbumMapper;
import de.lht.leafmusic3.mapper.ArtistMapper;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.AlbumRepository;
import de.lht.leafmusic3.repository.ArtistRepository;
import de.lht.leafmusic3.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongMapper songMapper;
    private final ArtistMapper artistMapper;
    private final AlbumMapper albumMapper;

    public SearchResultDTO searchByName(String keyword) {
        List<SongDTO> songs = songMapper.toDTOs(songRepository.findByNameContainingIgnoreCase(keyword));
        List<ArtistDTO> artists = artistMapper.toDTOs(artistRepository.findByNameContainingIgnoreCase(keyword));
        List<AlbumDTO> albums = albumMapper.toDTOs(albumRepository.findByNameContainingIgnoreCase(keyword));

        return new SearchResultDTO(songs, artists, albums);
    }
}