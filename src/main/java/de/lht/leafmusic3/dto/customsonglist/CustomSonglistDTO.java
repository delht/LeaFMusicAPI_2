package de.lht.leafmusic3.dto.customsonglist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomSonglistDTO {
    private int idItem;
    private int idList;
    private int idSong;
}
