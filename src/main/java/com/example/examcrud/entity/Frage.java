package com.example.examcrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Mehrere Fragen können ein Fach haben
    @ManyToOne
    @JoinColumn(name = "id_fach")
    @JsonManagedReference
    Fach faecher;

    // Mehrere Fragen können ein Themengebiet haben
    @ManyToOne
    @JoinColumn(name = "id_themengebiet")
    @JsonManagedReference
    Themengebiet themengebiet;

    // Eine Frage kann mehrere Antworten haben
    @OneToMany(mappedBy = "frage")
    @JsonBackReference
    private List<Antwort> antwortListe;

    //// CONSTRUCTOR ////

}
