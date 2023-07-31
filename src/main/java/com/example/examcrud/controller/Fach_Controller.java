package com.example.examcrud.controller;

import com.example.examcrud.dto.FachDTO;
import com.example.examcrud.dto.Request_FachDTO;
import com.example.examcrud.dto.Response_FachDTO;
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
    public ResponseEntity<?> addNewFach(@RequestBody FachDTO fachDTO) {
        Response_FachDTO responseFachDTO;
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
        List<Response_FachDTO> responseFachDTOList;
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
        Response_FachDTO responseFachDTO;
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
//    public Response_FachDTO updateOneFach(@PathVariable int fachId) {
//        return fachService.updateOneFach(fachId);
//    }
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
    public Response_FachDTO deleteOneFach(@PathVariable int fachId) {
        return fachService.deleteFach(fachId);
    }
}
