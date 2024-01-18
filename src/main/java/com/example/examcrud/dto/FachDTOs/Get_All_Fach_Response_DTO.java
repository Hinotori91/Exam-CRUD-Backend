package com.example.examcrud.dto.FachDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Get_All_Fach_Response_DTO {
	private int id;
	private String name;
	private Integer countThemengebiete;

}

