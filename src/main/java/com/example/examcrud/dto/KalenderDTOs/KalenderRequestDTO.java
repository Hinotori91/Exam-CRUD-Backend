package com.example.examcrud.dto.KalenderDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KalenderRequestDTO {
    private long millisekunden; // Für das genaue Datum inklusive Uhrzeit
    private String timeStart;
    private String timeEnd;
    private String event;
    private String kategorie;
}
