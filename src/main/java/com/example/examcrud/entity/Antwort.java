package com.example.examcrud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Antwort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Mehrere Antworten können eine Frage haben
    @ManyToOne
    @JoinColumn(name = "id_Frage")
    @JsonManagedReference
    private Frage frage;
//    private boolean true;

    //// CONSTRUCTOR ////

}
