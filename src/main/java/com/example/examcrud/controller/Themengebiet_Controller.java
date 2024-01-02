package com.example.examcrud.controller;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.FrageDTOs.GewichtungFrage_Response_DTO;
import com.example.examcrud.dto.FrageDTOs.ProzentRichtigDTO;
import com.example.examcrud.dto.ThemengebietDTOs.*;
import com.example.examcrud.service.Themengebiet_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themengebiet")
public class Themengebiet_Controller {

	@Autowired
	Themengebiet_Service themengebietService;

	@GetMapping
	public ResponseEntity<?> getAllThemengebiete() {
		List<Get_All_Themengebiet_Response_DTO> getAllThemengebietResponseDto;
		try {
			getAllThemengebietResponseDto = themengebietService.getAllThemengebiete();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(getAllThemengebietResponseDto, HttpStatus.OK);
	}

	@GetMapping("/fachid/{fachId}")
	public ResponseEntity<?> getAllThemengebieteFromOneFach(@PathVariable int fachId) {
		List<ThemengebietDTO> allThemengebieteFromOneFachDTO;
		try {
			allThemengebieteFromOneFachDTO = themengebietService.getAllThemengebieteFromOneFach(fachId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(allThemengebieteFromOneFachDTO, HttpStatus.OK);
	}

	@GetMapping("/{themengebietId}")
	public ResponseEntity<?> getSingleThemengebiet(@PathVariable int themengebietId) {
		Get_One_Themengebiet_Response_DTO getOneFachResponseDto;
		try {
			getOneFachResponseDto = themengebietService.getSingleThemengebiet(themengebietId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(getOneFachResponseDto, HttpStatus.OK);
	}

	@GetMapping("/detail/{themengebietId}")
	public ResponseEntity<?> themengebietDetail(@PathVariable int themengebietId) {
		Get_One_Detailed_Themengebiet_DTO getOneDetailedThemengebietDto;
		try {
			getOneDetailedThemengebietDto = themengebietService.themengebietDetail(themengebietId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(getOneDetailedThemengebietDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewThemengebeit(@RequestBody Add_Themengebiet_Request_DTO requestThemengebetDTO) {
		Add_Themengebiet_Response_DTO responseDTO;
		try {
			responseDTO = themengebietService.addNewThemengebiet(requestThemengebetDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


	@DeleteMapping("/{themengebietId}")
	public ResponseEntity<?> deleteThemengebiet(@PathVariable int themengebietId) {
		String message;
		try {
			message = themengebietService.deleteThemengebiet(themengebietId);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping("/{themengebietId}")
	public ResponseEntity<?> updateThemengebiet(@PathVariable int themengebietId, @RequestBody ThemengebietDTO themengebietDTO) {
		Update_Response_Themengebiet_DTO updateResponseThemengebietDto;
		try {
			updateResponseThemengebietDto = themengebietService.updateThemengebiet(themengebietId, themengebietDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(updateResponseThemengebietDto, HttpStatus.OK);
	}

	@GetMapping("/randomFrage/{themengebietId}")
	public ResponseEntity<?> getSchlechtesteFrageFromThemengebiet(@PathVariable int themengebietId) {
		FrageDTO frage;
		try {
			frage = themengebietService.getSchlechtesteFrageFromThemengebiet(themengebietId);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag mit dieser Id gefunden", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(frage, HttpStatus.OK);
	}

	@PutMapping("/randomFrage/update/{frageId}")
	public ResponseEntity<?> updateFrageMitGewichtThemengebiet(@PathVariable int frageId, @RequestBody ProzentRichtigDTO prozentRichtigDTO) {
		GewichtungFrage_Response_DTO gewichtungFrageResponseDto;
		try {
			gewichtungFrageResponseDto = themengebietService.updateFrageMitGewichtThemengebiet(frageId, prozentRichtigDTO);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag gefunden", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gewichtungFrageResponseDto, HttpStatus.OK);

	}
}
