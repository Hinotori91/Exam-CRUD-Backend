package com.example.examcrud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	// Ein Fach kann mehrere Fragen haben!
	@OneToMany(mappedBy = "faecher", cascade = CascadeType.ALL)
	private List<Frage> frageListe;

	// Ein Fach kann mehrere Themengebiete haben
	@OneToMany(mappedBy = "fach", cascade = CascadeType.ALL)
	private List<Themengebiet> themengebietListe;
}
