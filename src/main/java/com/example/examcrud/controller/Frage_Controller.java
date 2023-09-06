package com.example.examcrud.controller;

import com.example.examcrud.dto.AntwortenDTOs.Update_Antwort_Request_DTO;
import com.example.examcrud.dto.AntwortenDTOs.Update_Antwort_Response_DTO;
import com.example.examcrud.dto.FrageDTOs.*;
import com.example.examcrud.service.Frage_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frage")
public class Frage_Controller {

    @Autowired
    Frage_Service frageService;

    //    @CrossOrigin(origins = "http://localhost:5173") // Später für die richtige Connection zum Port
    @GetMapping("/{themengebietId}")
    public List<FrageDTO> getAllFragenVonThemengebietId(@PathVariable int themengebietId) {
        return frageService.getAllFragenVonThemengebietId(themengebietId);
    }

    @GetMapping("/singleFrage/{frageId}")
    public SingleFrageDTO getOneFrage(@PathVariable int frageId) {
        return frageService.getOneFrage(frageId);
    }

    @PostMapping
    public ResponseEntity<?> addNewFrage(@RequestBody Add_Frage_Request_DTO frageRequestDto) {
        Add_Frage_Response_DTO responseFrageDTO;
        try {
            responseFrageDTO = frageService.addNewFrage(frageRequestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseFrageDTO, HttpStatus.OK);
    }

    @PutMapping("/{frageId}")
    public ResponseEntity<?> updateFrage(@RequestBody Update_Antwort_Request_DTO updateAntwortRequestDto, @PathVariable int frageId) {
        Update_Frage_Response_DTO updateFrageResponseDto;
        try {
            updateFrageResponseDto = frageService.updateFrage(updateAntwortRequestDto, frageId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(updateFrageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{frageId}")
    public ResponseEntity<?> deleteFrage(@PathVariable int frageId){
        String message;
        try {
            message = frageService.deleteFrage(frageId);
        }catch (Exception e){
            return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
