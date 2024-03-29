package com.example.examcrud.service;

import com.example.examcrud.algorithmus.Algorithmus;
import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.FrageDTOs.Frage_DetailedThemengebiet_DTO;
import com.example.examcrud.dto.FrageDTOs.GewichtungFrage_Response_DTO;
import com.example.examcrud.dto.FrageDTOs.ProzentRichtigDTO;
import com.example.examcrud.dto.ThemengebietDTOs.*;
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
import java.util.List;
import java.util.Optional;

@Service
public class Themengebiet_Service {
	@Autowired
	Themengebiet_Repository themengebietRepository;
	@Autowired
	Fach_Repository fachRepository;
	@Autowired
	Frage_Repository frageRepository;

	public List<Get_All_Themengebiet_Response_DTO> getAllThemengebiete() {
		List<Get_All_Themengebiet_Response_DTO> tgList = new ArrayList<>();

		for (Themengebiet tg : themengebietRepository.findAll()) {
			tgList.add(Get_All_Themengebiet_Response_DTO.builder()
					.id(tg.getId())
					.name(tg.getName())
					.fachId(tg.getFach().getId())
					.build());
		}

		return tgList;
	}

	public List<ThemengebietDTO> getAllThemengebieteFromOneFach(int fachId) {
		Optional<Fach> fach = fachRepository.findById(fachId);

		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


		List<ThemengebietDTO> themengebietListDTO = new ArrayList<>();

		for (Themengebiet tg : themengebietRepository.findByFach(fach.get())) {
			themengebietListDTO.add(ThemengebietDTO.builder()
					.id(tg.getId())
					.name(tg.getName())
					.countFragen(frageRepository.countByThemengebiet(tg))
					.build());
		}

		return themengebietListDTO;
	}

	public Get_One_Themengebiet_Response_DTO getSingleThemengebiet(int themengebietId) {
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return Get_One_Themengebiet_Response_DTO.builder()
				.id(themengebietId)
				.name(themengebiet.get().getName())
				.fachId(themengebiet.get().getFach().getId())
				.build();
	}

	public Get_One_Detailed_Themengebiet_DTO themengebietDetail(int themengebietId) {
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		List<Frage_DetailedThemengebiet_DTO> frageDTOList = new ArrayList<>();

		for (Frage frage : frageRepository.findByThemengebiet(themengebiet.get())) {
			frageDTOList.add(Frage_DetailedThemengebiet_DTO.builder()
					.id(frage.getId())
					.name(frage.getName())
					.build());
		}


		return Get_One_Detailed_Themengebiet_DTO.builder()
				.id(themengebietId)
				.name(themengebiet.get().getName())
				.frageDTOList(frageDTOList)
				.fachId(themengebiet.get().getFach().getId())
				.build();
	}

	public Add_Themengebiet_Response_DTO addNewThemengebiet(Add_Themengebiet_Request_DTO requestThemengebetDTO) {
		Optional<Fach> fach = fachRepository.findById(requestThemengebetDTO.getFachId());

		if (fach.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}

		Themengebiet themengebiet = Themengebiet.builder()
				.name(requestThemengebetDTO.getName())
				.fach(fach.get())
				.build();
		themengebietRepository.save(themengebiet);

		return Add_Themengebiet_Response_DTO.builder()
				.id(themengebiet.getId())
				.name(themengebiet.getName())
				.fachId(fach.get().getId())
				.build();
	}

	public String deleteThemengebiet(int themengebietId) {
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		themengebietRepository.delete(themengebiet.get());
		return "Erfolgreich gelöscht";
	}

	public Update_Response_Themengebiet_DTO updateThemengebiet(int themengebietId, ThemengebietDTO themengebietDTO) {
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}

		themengebiet.get().setName(themengebietDTO.getName());
		themengebietRepository.save(themengebiet.get());

		return Update_Response_Themengebiet_DTO.builder()
				.id(themengebiet.get().getId())
				.name(themengebiet.get().getName())
				.build();
	}

	public FrageDTO getSchlechtesteFrageFromThemengebiet(int themengebietId) {
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Frage frage = Algorithmus.getNextFrage(frageRepository.findByThemengebiet(themengebiet.get()));

		return FrageDTO.builder()
				.id(frage.getId())
				.name(frage.getName())
				.themengebietId(frage.getThemengebiet().getId())
				.faecherId(frage.getFaecher().getId())
				.build();
	}

	public GewichtungFrage_Response_DTO updateFrageMitGewichtThemengebiet(int frageId, ProzentRichtigDTO prozentRichtig) {
		Optional<Frage> frage = frageRepository.findById(frageId);

		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		frage.get().setAltlast(Algorithmus.calculateNeulast(
				prozentRichtig.getProzentRichtig(),
				frage.get().getAltlast()));
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

