package com.example.examcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kalender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long millisekunden; // Für das Genaue Datume inklusive Uhrzeit
    private String timeStart;
    private String timeEnd;
    private String event;
    private String kategorie;

}
