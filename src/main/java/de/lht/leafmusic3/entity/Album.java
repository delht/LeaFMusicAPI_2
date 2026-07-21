package de.lht.leafmusic3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "albums")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idAlbum;

    private String name;
    private String imageUrl;

    private LocalDate releaseDate;
    private int idArtist;
    private String uploadBy;
    private LocalDateTime createdAt;


}
