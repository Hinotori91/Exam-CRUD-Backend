package com.example.examcrud.dto.FrageDTOs;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GewichtungFrage_Request_DTO {
	private double altlast;
	private Instant lastTry;
}
