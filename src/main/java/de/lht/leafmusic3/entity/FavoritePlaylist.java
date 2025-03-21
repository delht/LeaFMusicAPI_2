package de.lht.leafmusic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_playlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePlaylist {
    @Id
    @Column(name = "id_playlist")
    private int idPlaylist;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "id_song")
    private int idSong;

}
