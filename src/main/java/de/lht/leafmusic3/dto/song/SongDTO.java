package de.lht.leafmusic3.dto.song;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SongDTO {
    private int idSong;
    private String name;
    private int play;
    private String imageUrl;
    private String fileUrl;
    private LocalDateTime releaseDate;
    private int idArtist;
    private int idAlbum;
    private int idGenre;



}
