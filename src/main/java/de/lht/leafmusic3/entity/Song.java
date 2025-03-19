package de.lht.leafmusic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @Column(name = "id_song")
    private int idSong;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "id_artist")
    private int idArtist;

    @Column(name = "id_album")
    private int idAlbum;

    @Column(name = "id_genre")
    private int idGenre;

}
