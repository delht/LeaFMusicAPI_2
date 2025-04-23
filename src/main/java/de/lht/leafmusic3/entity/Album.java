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
@Table(name = "albums")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @Column(name = "id_album")
    private int idAlbum;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "id_artist")
    private int idArtist;

    @Column(name = "upload_by")
    private String uploadBy;

}
