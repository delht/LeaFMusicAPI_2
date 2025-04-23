package de.lht.leafmusic3.dto.album;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlbumDTO {
    private int idAlbum;
    private String name;
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime releaseDate;
    private int idArtist;

    private String uploadBy;
}
