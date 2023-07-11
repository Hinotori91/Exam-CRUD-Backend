package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Frage_Repository;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Frage_Service {

    @Autowired
    Frage_Repository frageRepository;
    @Autowired
    Themengebiet_Service themengebietService;
    @Autowired
    Themengebiet_Repository themengebietRepository;

    public List<FrageDTO> getAllFragenVonThemengebietId(int themengebietId) {
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

        if (themengebiet.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return frageRepository.findByThemengebiet(themengebiet.get());
    }
}