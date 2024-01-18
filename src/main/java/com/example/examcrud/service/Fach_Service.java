package com.example.examcrud.service;

import com.example.examcrud.algorithmus.Algorithmus;
import com.example.examcrud.dto.FachDTOs.*;
import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.FrageDTOs.GewichtungFrage_Response_DTO;
import com.example.examcrud.dto.FrageDTOs.ProzentRichtigDTO;
import com.example.examcrud.dto.ThemengebietDTOs.ThemengebietDTO;
import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Fach_Repository;
import com.example.examcrud.repository.Frage_Repository;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Fach_Service {
	@Autowired
	Fach_Repository fachRepository;
	@Autowired
	Frage_Repository frageRepository;

	@Autowired
	Themengebiet_Repository themengebietRepository;

	// Neues Fach hinzufügen
	public Add_Fach_Response_DTO addNewFach(Add_Fach_Request_DTO fachDTO) {
		Fach fach = Fach.builder().name(fachDTO.getName()).build();
		fachRepository.save(fach);

		return Add_Fach_Response_DTO.builder()
				.id(fach.getId())
				.name(fach.getName())
				.build();
	}

	// Alle Fächer werden ausgegeben -> Nur ID und Name
	public List<Get_All_Fach_Response_DTO> getAllFaecher() {
		List<Fach> fachList = fachRepository.findAll();
		List<Get_All_Fach_Response_DTO> responseList = new ArrayList<>();


		for (Fach element : fachList) {


			responseList.add(Get_All_Fach_Response_DTO.builder()
					.id(element.getId())
					.name(element.getName())
					.countThemengebiete(themengebietRepository.countByFach(element))
					.build());
		}
		return responseList;
	}

	// Ein Fach wird anhand der ID ausgegeben inklusive Themengebiete
	public Get_One_Fach_Response_DTO getOneFach(int fachId) {
		Optional<Fach> fach = fachRepository.findById(fachId);

		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			List<Themengebiet> themengebietList = fach.get().getThemengebietListe();
			List<ThemengebietDTO> themengebietDTOList = new ArrayList<>();

			for (Themengebiet themengebiet : themengebietList) {
				themengebietDTOList.add(ThemengebietDTO.builder()
						.id(themengebiet.getId())
						.name(themengebiet.getName())
						.build());
			}

			if (themengebietDTOList.isEmpty()) {
				themengebietDTOList = Collections.emptyList();
			}

			return Get_One_Fach_Response_DTO.builder()
					.id(fach.get().getId())
					.name(fach.get().getName())
					.themengebietListe(themengebietDTOList)
					.build();
		}
	}

	// Fach bearbeiten -> Name kann geändert werden
	public Response_FachDTO updateOneFach(int fachId, FachDTO fachDTO) {
		Optional<Fach> fach = fachRepository.findById(fachId);

		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		fach.get().setName(fachDTO.getName());
		fachRepository.save(fach.get());

		return Response_FachDTO.builder()
				.id(fachId)
				.name(fach.get().getName())
				.build();
	}

	// Ein Fach kann gelöscht werden... Alle Themengebiete, Fragen und Antworten werden mit gelöscht!
	public String deleteFach(int fachId) {
		Optional<Fach> fach = fachRepository.findById(fachId);
		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		fachRepository.delete(fach.get());
		return "Erfolgreich gelöscht!";
	}

	public FrageDTO getSchlechtesteFrageFromFach(int fachId) {
		Optional<Fach> fach = fachRepository.findById(fachId);

		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		List<Frage> frageListe = frageRepository.findByFaecher(fach.get());

		Frage frage = Algorithmus.getNextFrage(frageListe);

		return FrageDTO.builder()
				.id(frage.getId())
				.name(frage.getName())
				.themengebietId(frage.getThemengebiet().getId())
				.faecherId(frage.getFaecher().getId())
				.build();
	}


	//    public GewichtungFrage_Response_DTO updateFrageMitGewicht(int frageId, GewichtungFrage_Request_DTO gewichtungFrageRequestDto) {
	public GewichtungFrage_Response_DTO updateFrageMitGewicht(int frageId, ProzentRichtigDTO prozentRichtig) {
		Optional<Frage> frage = frageRepository.findById(frageId);

		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		frage.get().setAltlast(Algorithmus.calculateNeulast(prozentRichtig.getProzentRichtig(), frage.get().getAltlast()));
		frage.get().setLastTry(Instant.now());
		frageRepository.save(frage.get());

		return GewichtungFrage_Response_DTO.builder()
				.id(frage.get().getId())
				.name(frage.get().getName())
				.faecherId(frage.get().getFaecher().getId())
				.themengebietId(frage.get().getThemengebiet().getId())
				.build();
	}
}
