package com.example.examcrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Ein Fach kann mehrere Fragen haben!
    @OneToMany(mappedBy = "faecher")
    @JsonBackReference
    private List<Frage> frageListe;

//    // Mehrere Fächer können mehrere Themengebiete haben! (Hilfstabelle Fach_Themengebiet)
//    @OneToMany(mappedBy = "fach")
//    @JsonBackReference
//    private List<Fach_Themengebiet> themengebietList;

    // Ein Fach kann mehrere Themengebiete haben
    @OneToMany(mappedBy = "fach")
    private List<Themengebiet> themengebietListe;

    //// CONSTRUCTOR ////

}
