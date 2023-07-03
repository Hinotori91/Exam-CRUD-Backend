package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Frage_Repository;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Frage_Service {

    @Autowired
    Frage_Repository frageRepository;
    @Autowired
    Themengebiet_Service themengebietService;

//    public List<FrageDTO> getFragenVonThemengebiet(int themengebiet) {
//        List<FrageDTO> liste = new ArrayList<>();
//        List<Themengebiet> themengebietList = themengebietService.getAllThemengebiete();
//
//        for (Themengebiet thema : themengebietList) {
//
//        }
////        for(Frage frage : )
//        return liste;
//    }

//    public List<FrageDTO> getAllFragenVonThemengebietId(int themengebietId) {
//        return frageRepository.getAllFragenByThemengebietId(themengebietId);
//    }
}