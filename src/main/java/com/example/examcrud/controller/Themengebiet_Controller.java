package com.example.examcrud.controller;

import com.example.examcrud.dto.ThemengebietDTO;
import com.example.examcrud.service.Themengebiet_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/themengebiet")
public class Themengebiet_Controller {

    @Autowired
    Themengebiet_Service themengebietService;

    @GetMapping
    public List<ThemengebietDTO> getAllThemengebiete(){
        return themengebietService.getAllThemengebiete();
    }

//    @GetMapping
//    public ThemengebietDTO getSingleThemengebiet(int themengebietId){
//        try {
//            themengebietService.getSingleThemengebiet(themengebietId);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//
//
//        return themengebietService.getSingleThemengebiet(themengebietId);
//    }
}
