package com.example.examcrud.dto.ThemengebietDTOs;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request_ThemengebetDTO {
	private int id;
	private String name;
	private List<FrageDTO> fragenListe;
	private int fachId;
}
