package com.example.examcrud.controller;

import com.example.examcrud.dto.FrageDTOs.Add_Frage_Request_DTO;
import com.example.examcrud.dto.FrageDTOs.Add_Frage_Response_DTO;
import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.FrageDTOs.SingleFrageDTO;
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
    public SingleFrageDTO getOneFrage(@PathVariable int frageId){
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
}
