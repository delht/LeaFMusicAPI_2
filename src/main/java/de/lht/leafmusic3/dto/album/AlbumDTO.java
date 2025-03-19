package de.lht.leafmusic3.dto.album;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlbumDTO {
    private int idAlbum;
    private String name;
    private String imageUrl;
    private LocalDateTime releaseDate;
    private int idArtist;
}
