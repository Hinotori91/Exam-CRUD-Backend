package com.example.examcrud.dto.AntwortenDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Antwort_Request_DTO {
	private String name;
	private Boolean richtig;
	private int frageId;
}
