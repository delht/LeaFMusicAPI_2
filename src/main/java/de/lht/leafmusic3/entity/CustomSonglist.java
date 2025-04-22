package de.lht.leafmusic3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "id_item")
    private int idItem;

    @Column(name = "id_list")
    private int idList;

    @Column(name = "id_song")
    private int idSong;


}
