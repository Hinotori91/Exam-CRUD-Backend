package com.example.examcrud.dto.FrageDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleFrageDTO {
	private int id;
	private String name;
	private boolean examMode;
}
