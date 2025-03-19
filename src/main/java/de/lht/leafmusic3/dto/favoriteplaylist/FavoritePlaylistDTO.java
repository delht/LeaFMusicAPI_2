package de.lht.leafmusic3.dto.favoriteplaylist;

import lombok.Data;

@Data
public class FavoritePlaylistDTO {
    private int idPlaylist;
    private String name;
    private int idUser;
    private int idSong;
}
