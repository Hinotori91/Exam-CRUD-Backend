package com.example.examcrud.controller;

import com.example.examcrud.dto.AntwortenDTOs.*;
import com.example.examcrud.service.Antwort_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/antwort")
public class Antwort_Controller {
	@Autowired
	Antwort_Service antwortService;

	@GetMapping("/{frageId}")
	public ResponseEntity<?> getAllAntwortenFromFrage(@PathVariable int frageId) {
		List<AntwortDTO> antwortListDTO;
		try {
			antwortListDTO = antwortService.getAllAntwortenFromFrage(frageId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(antwortListDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewAntwortToFrage(@RequestBody Add_Antwort_Request_DTO addAntwortRequestDto) {
		Add_Antwort_Response_DTO addAntwortResponseDto;
		try {
			addAntwortResponseDto = antwortService.addAntwortToFrage(addAntwortRequestDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(addAntwortResponseDto, HttpStatus.OK);
	}

	@PutMapping("/{antwortId}")
	public ResponseEntity<?> updateAntwort(@RequestBody Update_Antwort_Request_DTO updateAntwortRequestDto, @PathVariable int antwortId) {
		Update_Antwort_Response_DTO updateAntwortResponseDto;
		try {
			updateAntwortResponseDto = antwortService.updateAntwort(updateAntwortRequestDto, antwortId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(updateAntwortResponseDto, HttpStatus.OK);
	}

	@DeleteMapping("/{antwortId}")
	public ResponseEntity<?> deleteAntwort(@PathVariable int antwortId) {
		String message;
		try {
			message = antwortService.deleteAntwort(antwortId);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
