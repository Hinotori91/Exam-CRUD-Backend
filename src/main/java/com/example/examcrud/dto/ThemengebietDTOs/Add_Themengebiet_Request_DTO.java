package com.example.examcrud.dto.ThemengebietDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Themengebiet_Request_DTO {
	private String name;
	private int fachId;
}
