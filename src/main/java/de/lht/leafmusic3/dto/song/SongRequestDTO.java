package de.lht.leafmusic3.dto.song;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SongRequestDTO {
    private String name;
    private int play;
    private LocalDateTime releaseDate;
    private int idArtist;
    private int idAlbum;
    private int idGenre;

}
