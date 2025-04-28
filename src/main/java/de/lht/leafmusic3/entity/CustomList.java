package de.lht.leafmusic3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_list")
    private int idList;

    @Column(name = "name")
    private String name;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "state")
    private int state;
}
