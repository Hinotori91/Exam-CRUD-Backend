package com.example.examcrud.dto.FachDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Fach_Request_DTO {
	private String name;
	private int fachId;
	private int themengebietId;
}
