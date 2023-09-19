package com.example.examcrud.dto.KalenderDTOs;

import lombok.*;

import java.sql.Time;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KalenderDTO {
    private int id;
    private long millisekunden; // Für das genaue Datum inklusive Uhrzeit
    private String timeStart;
    private String timeEnd;
    private String event;
    private String kategorie;
}
