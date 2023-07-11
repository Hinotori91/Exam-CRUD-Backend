package com.example.examcrud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean richtig = true;

    //// CONSTRUCTOR ////

}
