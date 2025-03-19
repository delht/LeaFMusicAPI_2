package de.lht.leafmusic3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @Column(name = "id_artist")
    private int idArtist;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;
}
