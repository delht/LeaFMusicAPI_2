package de.lht.leafmusic3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album_Request {
    private int idAlbum;
    private String name;
    private String imageUrl;
    private LocalDateTime releaseDate;
    private int idArtist;
}
