package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.mapper.ArtistMapper;
import de.lht.leafmusic3.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        System.out.println(artists);
        return artistMapper.toDTOs(artists);
    }
}
