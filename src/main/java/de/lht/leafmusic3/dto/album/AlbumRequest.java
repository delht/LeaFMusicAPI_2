package de.lht.leafmusic3.dto.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumRequest {

    private String name;
    private String imageUrl;

    private LocalDate releaseDate;
    private int idArtist;
    private String uploadBy;
    private LocalDateTime createdAt;
}
