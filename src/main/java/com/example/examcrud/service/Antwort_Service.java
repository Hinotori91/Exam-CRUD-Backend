package com.example.examcrud.service;

import com.example.examcrud.dto.AntwortenDTOs.*;
import com.example.examcrud.entity.Antwort;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.repository.Antwort_Repository;
import com.example.examcrud.repository.Frage_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

@Service
public class Antwort_Service {
	@Autowired
	Antwort_Repository antwortRepository;
	@Autowired
	Frage_Repository frageRepository;


	public List<AntwortDTO> getAllAntwortenFromFrage(int frageId) {
		Optional<Frage> frage = frageRepository.findById(frageId);
		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		List<AntwortDTO> antwortDTOList = new ArrayList<>();

		for (Antwort antwort : antwortRepository.findByFrage(frage.get())) {
			antwortDTOList.add(toAntwortDTO(antwort));
		}

		return antwortDTOList;
	}


	public List<AntwortDTO> getPlayAntwortenFromFrage(int frageId) {
		Optional<Frage> frage = frageRepository.findById(frageId);
		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		List<Antwort> antwortList = antwortRepository.findByFrage(frage.get());
		Collections.shuffle(antwortList);

		if (frage.get().isExamMode()) {
			return antwortList.stream()
					.map(Antwort_Service::toAntwortDTO)
					.toList();
		}

		Optional<Antwort> correct = antwortList.stream()
				.filter(a -> a.isRichtig())
				.findAny();

		// at least one answer has to be correct!
		if (correct.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// three random incorrect answers
		List<Antwort> incorrect = antwortList.stream()
				.filter(a -> !a.isRichtig())
				.limit(3)
				.toList();

		return Stream.concat( // merging the correct and the incorrect elements
						Stream.of(correct.get()),
						incorrect.stream())
				.map(Antwort_Service::toAntwortDTO)
				.toList();
	}

	private static AntwortDTO toAntwortDTO(Antwort a) {
		return AntwortDTO.builder()
				.id(a.getId())
				.name(a.getName())
				.richtig(a.isRichtig())
				.build();
	}

	public Add_Antwort_Response_DTO addAntwortToFrage(Add_Antwort_Request_DTO addAntwortRequestDto) {
		Optional<Frage> frage = frageRepository.findById(addAntwortRequestDto.getFrageId());

		if (frage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Antwort antwort = Antwort.builder()
				.name(addAntwortRequestDto.getName())
				.richtig(addAntwortRequestDto.getRichtig())
				.frage(frage.get())
				.build();

		antwortRepository.save(antwort);

		return Add_Antwort_Response_DTO.builder()
				.id(antwort.getId())
				.name(antwort.getName())
				.richtig(antwort.isRichtig())
				.build();
	}

	public Update_Antwort_Response_DTO updateAntwort(Update_Antwort_Request_DTO updateAntwortRequestDto, int antwortId) {
		Optional<Antwort> antwort = antwortRepository.findById(antwortId);

		if (antwort.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		antwort.get().setName(updateAntwortRequestDto.getName());
		antwort.get().setRichtig(updateAntwortRequestDto.getRichtig());
		antwortRepository.save(antwort.get());

		return Update_Antwort_Response_DTO.builder()
				.id(antwort.get().getId())
				.name(antwort.get().getName())
				.richtig(antwort.get().isRichtig())
				.build();
	}

	public String deleteAntwort(int antwortId) {
		Optional<Antwort> antwort = antwortRepository.findById(antwortId);

		if (antwort.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		antwortRepository.delete(antwort.get());
		return "Erfolgreich gelöscht!";
	}
}
