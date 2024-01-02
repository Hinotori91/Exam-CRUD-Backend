package com.example.examcrud.dto.FrageDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Frage_Request_DTO {
	private String name;
	private int fachId;
	private int themengebietId;
	private boolean examMode;
}
