package com.example.examcrud.controller;

import com.example.examcrud.dto.FachDTOs.Get_One_Fach_Response_DTO;
import com.example.examcrud.dto.ThemengebietDTOs.*;
import com.example.examcrud.service.Themengebiet_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themengebiet")
public class Themengebiet_Controller {

    @Autowired
    Themengebiet_Service themengebietService;

    @GetMapping
    public ResponseEntity<?> getAllThemengebiete(){
        List<Get_All_Themengebiet_Response_DTO> getAllThemengebietResponseDto;
        try {
            getAllThemengebietResponseDto = themengebietService.getAllThemengebiete();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(getAllThemengebietResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{themengebietId}")
    public ResponseEntity<?> getSingleThemengebiet(@PathVariable int themengebietId){
        Get_One_Themengebiet_Response_DTO getOneFachResponseDto;
        try {
            getOneFachResponseDto = themengebietService.getSingleThemengebiet(themengebietId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(getOneFachResponseDto, HttpStatus.OK);
    }

    @GetMapping("/detail/{themengebietId}")
    public ResponseEntity<?> themengebietDetail (@PathVariable int themengebietId){
        Get_One_Detailed_Themengebiet_DTO getOneDetailedThemengebietDto;
        try {
            getOneDetailedThemengebietDto = themengebietService.themengebietDetail(themengebietId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(getOneDetailedThemengebietDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewThemengebeit(@RequestBody Add_Themengebiet_Request_DTO requestThemengebetDTO){
    Add_Themengebiet_Response_DTO responseDTO;
    try{
        responseDTO = themengebietService.addNewThemengebiet(requestThemengebetDTO);
    }catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{themengebietId}")
    public ResponseEntity<?> deleteThemengebiet(@PathVariable int themengebietId){
        String message;
        try {
            message = themengebietService.deleteThemengebiet(themengebietId);
        }catch (Exception e){
            return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
