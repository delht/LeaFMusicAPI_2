package de.lht.leafmusic3.dto.search;

import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.genre.GenreDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResultDTO2 {
    private List<SongDTO> songs;
    private List<ArtistDTO> artists;
    private List<AlbumDTO> albums;
    private List<GenreDTO> genres;

    public SearchResultDTO2() {
        this.songs = List.of();
        this.artists = List.of();
        this.albums = List.of();
        this.genres = List.of();
    }

}
