package com.example.examcrud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Themengebiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Ein Themengebiet kann mehrere Fragen haben
    @OneToMany(mappedBy = "themengebiet")
    private List<Frage> fragenListe;

//    // Mehrere Themengebiete können mehrere Fächer haben (zb OOP gibt es in Java und in Kotlin)
//    // Hilfstabelle Fach_Themengebiet
//    @OneToMany(mappedBy = "themengebiet")
//    private List<Fach_Themengebiet> fachListe;

    // Mehrere Themengebiete können ein Fach haben
    @ManyToOne
    @JoinColumn(name = "fachId")
    private Fach fach;

    //// CONSTRUCTOR ////

}
