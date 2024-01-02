package com.example.examcrud.dto.ThemengebietDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Themengebiet_Response_DTO {
	private int id;
	private String name;
	private int fachId;
}
