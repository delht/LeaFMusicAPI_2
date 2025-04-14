package de.lht.leafmusic3.dto.song;


import lombok.Data;
import java.time.LocalDateTime;

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
