package com.example.examcrud.dto.FrageDTOs;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GewichtungFrage_Response_DTO {
    private int id;
    private String name;
    private int faecherId;
    private int themengebietId;
}
