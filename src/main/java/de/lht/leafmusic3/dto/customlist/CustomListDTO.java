package de.lht.leafmusic3.dto.customlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomListDTO {
    private int idList;
    private String name;
    private String idUser;
    private int state;
}
