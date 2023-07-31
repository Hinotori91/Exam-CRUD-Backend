package com.example.examcrud.controller;

import com.example.examcrud.dto.Request_ThemengebetDTO;
import com.example.examcrud.dto.Response_ThemengebietDTO;
import com.example.examcrud.dto.ThemengebietDTO;
import com.example.examcrud.service.Themengebiet_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{themengebietId}")
    public Response_ThemengebietDTO getSingleThemengebiet(int themengebietId){
        return themengebietService.getSingleThemengebiet(themengebietId);
    }

    @PostMapping
    public Response_ThemengebietDTO addNewThemengebiet(@RequestBody Request_ThemengebetDTO requestThemengebetDTO){
        return themengebietService.addNewThemengebiet(requestThemengebetDTO);
    }
}
