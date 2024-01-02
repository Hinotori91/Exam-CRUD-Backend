package com.example.examcrud.controller;

import com.example.examcrud.dto.KalenderDTOs.KalenderDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderMillisDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderRequestDTO;
import com.example.examcrud.service.Kalender_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/kalender")
public class Kalender_Controller {
	@Autowired
	Kalender_Service kalenderService;

	@PostMapping
	public ResponseEntity<?> addNewEvent(@RequestBody KalenderRequestDTO kalenderRequestDTO) {
		KalenderDTO responseKalenderDto;
		try {
			responseKalenderDto = kalenderService.addNewEvent(kalenderRequestDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(responseKalenderDto, HttpStatus.OK);
	}

	@PostMapping("/get")
	public ResponseEntity<?> getEventsByDate(@RequestBody KalenderMillisDTO kalenderMillisDTO) {
		List<KalenderDTO> responseKalenderDTOList;
		try {
			responseKalenderDTOList = kalenderService.getEventsbyDate(kalenderMillisDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseKalenderDTOList, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllEvents() {
		List<KalenderDTO> eventList = new ArrayList<>();
		try {
			eventList = kalenderService.getAllEvents();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(eventList, HttpStatus.OK);
	}

	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
		String message;
		try {
			message = kalenderService.deleteEvent(eventId);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/download")
	public ResponseEntity<?> downloadKalenderICS() {
//        byte[] byteArray;
//        Calendar byteArray;
		String byteArray;
		try {
			byteArray = kalenderService.downloadKalender();
		} catch (Exception e) {
			return new ResponseEntity<>("Fehlgeschlagen", HttpStatus.INTERNAL_SERVER_ERROR);
		}
//        return new ResponseEntity<>(byteArray, HttpStatus.OK);
		return ResponseEntity.ok()
				.header(
						"Content-Disposition",
						"attachment; filename=kalender.ics")
				.body(byteArray);
	}
}
