package de.lht.leafmusic3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_songlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomSonglist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int idItem;

    @Column(name = "id_list")
    private int idList;

    @Column(name = "id_song")
    private int idSong;


}
