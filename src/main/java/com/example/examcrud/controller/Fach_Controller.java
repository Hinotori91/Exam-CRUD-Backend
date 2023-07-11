package com.example.examcrud.controller;

import com.example.examcrud.dto.FachDTO;
import com.example.examcrud.dto.Request_FachDTO;
import com.example.examcrud.dto.Response_FachDTO;
import com.example.examcrud.repository.Fach_Repository;
import com.example.examcrud.service.Fach_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fach")
public class Fach_Controller {

    @Autowired
    Fach_Service fachService;

    @PostMapping
    public Response_FachDTO addNewFach(@RequestBody Request_FachDTO requestFachDTO){
        return fachService.addNewFach(requestFachDTO);
    }

    @GetMapping
    public List<Response_FachDTO> getAllFaecher(){
        return fachService.getAllFaecher();
    }

    @GetMapping("/{fachId}")
    public Response_FachDTO getOneFach(@PathVariable int fachId){
        return fachService.getOneFach(fachId);
    }

    @PutMapping("/{fachId}/{themengebietId}")
    public Response_FachDTO connectThemengebietToFach(@PathVariable int fachId, @PathVariable int themengebietId){
        return fachService.connectThemengebietToFach(fachId,themengebietId);
    }

}
