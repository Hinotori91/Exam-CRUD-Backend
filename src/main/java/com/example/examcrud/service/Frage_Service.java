package com.example.examcrud.service;

import com.example.examcrud.dto.AntwortenDTOs.Update_Antwort_Request_DTO;
import com.example.examcrud.dto.FrageDTOs.*;
import com.example.examcrud.entity.Antwort;
import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Antwort_Repository;
import com.example.examcrud.repository.Fach_Repository;
import com.example.examcrud.repository.Frage_Repository;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Frage_Service {

	@Autowired
	Frage_Repository frageRepository;
	@Autowired
	Antwort_Repository antwortRepository;
	@Autowired
	Themengebiet_Repository themengebietRepository;
	@Autowired
	Fach_Repository fachRepository;

	public List<FrageDTO> getAllFragenVonThemengebietId(int themengebietId) {
		List<FrageDTO> frageDTOList = new ArrayList<>();
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

		if (themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		List<Frage> frageList = frageRepository.findByThemengebiet(themengebiet.get());

		for (Frage frage : frageList) {
			frageDTOList.add(FrageDTO.builder()
					.id(frage.getId())
					.name(frage.getName())
					.faecherId(frage.getFaecher().getId())
					.themengebietId(frage.getThemengebiet().getId())
					.examMode((frage.isExamMode()))
					.build());
		}

		return frageDTOList;
	}

	public Add_Frage_Response_DTO addNewFrage(Add_Frage_Request_DTO frageRequestDto) {
		Optional<Fach> fach = fachRepository.findById(frageRequestDto.getFachId());
		Optional<Themengebiet> themengebiet = themengebietRepository.findById(frageRequestDto.getThemengebietId());

		if (fach.isEmpty() || themengebiet.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Frage frage = Frage.builder()
				.name(frageRequestDto.getName())
				.faecher(fach.get())
				.themengebiet(themengebiet.get())
				.examMode(frageRequestDto.isExamMode())
				.build();

		frageRepository.save(frage);

		return Add_Frage_Response_DTO.builder()
				.id(frage.getId())
				.name(frage.getName())
				.fachId(frage.getFaecher().getId())
				.themengebietId(frage.getThemengebiet().getId())
				.examMode(frage.isExamMode())
				.build();
	}

	public Add_Frage_Response_DTO duplicateFrage(int frageId) {
		Frage frage = getFrage(frageId);

		Frage newFrage = Frage.builder()
				.name(frage.getName())
				.faecher(frage.getFaecher())
				.themengebiet(frage.getThemengebiet())
				.examMode(frage.isExamMode())
				.build();

		frageRepository.save(newFrage);

		antwortRepository.findByFrage(frage).forEach(antwort -> {
			antwortRepository.save(Antwort.builder()
					.name(antwort.getName())
					.richtig(antwort.isRichtig())
					.frage(newFrage)
					.build());
		});

		return Add_Frage_Response_DTO.builder()
				.id(newFrage.getId())
				.name(newFrage.getName())
				.fachId(newFrage.getFaecher().getId())
				.themengebietId(newFrage.getThemengebiet().getId())
				.examMode(newFrage.isExamMode())
				.build();
	}

	public SingleFrageDTO getOneFrage(int frageId) {
		Frage frage = getFrage(frageId);

		return SingleFrageDTO.builder()
				.id(frage.getId())
				.name(frage.getName())
				.examMode(frage.isExamMode())
				.build();
	}

	public Update_Frage_Response_DTO updateFrage(Update_Antwort_Request_DTO updateAntwortRequestDto, int frageId) {
		Frage frage = getFrage(frageId);

		frage.setName(updateAntwortRequestDto.getName());
		frage.setExamMode(updateAntwortRequestDto.getExamMode());
		frageRepository.save(frage);

		return Update_Frage_Response_DTO.builder()
				.id(frage.getId())
				.name(frage.getName())
				.faecherId(frage.getFaecher().getId())
				.themengebietId(frage.getThemengebiet().getId())
				.examMode(frage.isExamMode())
				.build();
	}


	public String deleteFrage(int frageId) {
		frageRepository.delete(getFrage(frageId));
		return "Erfolgreich gelöscht";
	}

	private Frage getFrage(int frageId) {
		Optional<Frage> frage = frageRepository.findById(frageId);

		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return frage.get();
	}
}