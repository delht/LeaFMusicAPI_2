package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.mapper.ArtistMapper;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.ArtistRepository;
import de.lht.leafmusic3.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final SongMapper songMapper;
    private final SongRepository songRepository;

    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        System.out.println(artists);
        return artistMapper.toDTOs(artists);
    }

    public ArtistDTO getArtistById(String id) {
        String artistId = String.valueOf(id);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("ko cos"));
        return artistMapper.toDTO(artist);
    }

    public List<ArtistDTO> getRandomArtists(int limit) {
        List<Artist> artists = artistRepository.findRandomAritsts(limit);
        return artistMapper.toDTOs(artists);
    }


}
