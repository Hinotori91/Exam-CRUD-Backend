package com.example.examcrud.controller;

import com.example.examcrud.dto.FrageDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.service.Frage_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frage")
public class Frage_Controller {

    @Autowired
    Frage_Service frage_service;

//    @CrossOrigin(origins = "http://localhost:5173") // Später für die richtige Connection zum Port
//    @GetMapping("/{themengebietId}")
//    public List<FrageDTO> getAllFragenVonThemengebietId(@PathVariable int themengebietId){
//        return frage_service.getAllFragenVonThemengebietId(themengebietId);
//    }
}
