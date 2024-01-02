package com.example.examcrud.dto.FrageDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrageDTO {

	private int id;
	private String name;
	private int faecherId;
	private int themengebietId;
	//    private List<AntwortDTO> antwortListe;
	private boolean examMode;

}
