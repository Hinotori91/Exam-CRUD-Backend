package com.example.examcrud.controller;

import com.example.examcrud.dto.FachDTOs.*;
import com.example.examcrud.service.Fach_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fach")
public class Fach_Controller {

    @Autowired
    Fach_Service fachService;

    /*
     * Erstellung eines neuen Fachs
     */
    @PostMapping
    public ResponseEntity<?> addNewFach(@RequestBody Add_Fach_Request_DTO fachDTO) {
        Add_Fach_Response_DTO responseFachDTO;
        try {
            responseFachDTO = fachService.addNewFach(fachDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseFachDTO, HttpStatus.OK);
    }

    /*
     * Ausgabe aller bestehenden Fächer
     */
    @GetMapping
    public ResponseEntity<?> getAllFaecher() {
        List<Get_All_Fach_Response_DTO> responseFachDTOList;
        try {
            responseFachDTOList = fachService.getAllFaecher();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseFachDTOList, HttpStatus.OK);
    }

    /*
     * Ausgabe eines bestimmten Fachs
     */
    @GetMapping("/{fachId}")
    public ResponseEntity<?> getOneFach(@PathVariable int fachId) {
        Get_One_Fach_Response_DTO responseFachDTO;
        try {
            responseFachDTO = fachService.getOneFach(fachId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseFachDTO, HttpStatus.OK);
    }

    /*
     * Bearbeitung eines bestimmten Fachs
     */
    @PutMapping("/{fachId}")
    public ResponseEntity<?> updateOneFach(@PathVariable int fachId, @RequestBody FachDTO fachDTO){
        Response_FachDTO responseFachDTO;
        try {
            responseFachDTO = fachService.updateOneFach(fachId, fachDTO);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseFachDTO, HttpStatus.OK);
    }


    /*
     * Löschung eines bestimmten Fachs
     */
    @DeleteMapping("/{fachId}")
    public ResponseEntity <?> deleteOneFach(@PathVariable int fachId) {
        String message;
        try {
            message = fachService.deleteFach(fachId);
        }catch (Exception e){
            return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
