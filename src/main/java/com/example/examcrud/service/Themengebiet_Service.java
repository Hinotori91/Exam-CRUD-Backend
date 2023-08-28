package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.FrageDTOs.Frage_DetailedThemengebiet_DTO;
import com.example.examcrud.dto.ThemengebietDTOs.*;
import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Fach_Repository;
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
public class Themengebiet_Service {
    @Autowired
    Themengebiet_Repository themengebietRepository;
    @Autowired
    Fach_Repository fachRepository;
    @Autowired
    Frage_Repository frageRepository;

    public List<Get_All_Themengebiet_Response_DTO> getAllThemengebiete() {
        List<Themengebiet> themengebietList = themengebietRepository.findAll();
        List<Get_All_Themengebiet_Response_DTO> tgList = new ArrayList<>();

        for (Themengebiet tg : themengebietList) {
            tgList.add(Get_All_Themengebiet_Response_DTO.builder()
                    .id(tg.getId())
                    .name(tg.getName())
                    .fachId(tg.getFach().getId())
                    .build());
        }
        return tgList;
    }

    public List<ThemengebietDTO> getAllThemengebieteFromOneFach(int fachId) {
        Optional<Fach> fach = fachRepository.findById(fachId);
        List<Themengebiet> themengebietList;
        List<ThemengebietDTO> themengebietListDTO = new ArrayList<>();

        if (fach.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        themengebietList = themengebietRepository.findByFach(fach.get());
        for(Themengebiet tg : themengebietList){
            themengebietListDTO.add(ThemengebietDTO.builder()
                            .id(tg.getId())
                            .name(tg.getName())
                    .build());
        }

        return themengebietListDTO;
    }

    public Get_One_Themengebiet_Response_DTO getSingleThemengebiet(int themengebietId) {
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

        if (themengebiet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return Get_One_Themengebiet_Response_DTO.builder()
                .id(themengebietId)
                .name(themengebiet.get().getName())
                .fachId(themengebiet.get().getFach().getId())
                .build();
    }

    public Get_One_Detailed_Themengebiet_DTO themengebietDetail(int themengebietId) {
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

        if (themengebiet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Frage> frageList = frageRepository.findByThemengebiet(themengebiet.get());
        List<Frage_DetailedThemengebiet_DTO> frageDTOList = new ArrayList<>();

        for (Frage frage : frageList){
            frageDTOList.add(Frage_DetailedThemengebiet_DTO.builder()
                            .id(frage.getId())
                            .name(frage.getName())
                    .build());
        }


        return Get_One_Detailed_Themengebiet_DTO.builder()
                .id(themengebietId)
                .name(themengebiet.get().getName())
                .frageDTOList(frageDTOList)
                .fachId(themengebiet.get().getFach().getId())
                .build();
    }

    public Add_Themengebiet_Response_DTO addNewThemengebiet(Add_Themengebiet_Request_DTO requestThemengebetDTO) {
        System.out.println(requestThemengebetDTO.getFachId());

        Optional<Fach> fach = fachRepository.findById(requestThemengebetDTO.getFachId());

        if (fach.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        Themengebiet themengebiet = Themengebiet.builder()
                .name(requestThemengebetDTO.getName())
                .fach(fach.get())
                .build();
        themengebietRepository.save(themengebiet);

        return Add_Themengebiet_Response_DTO.builder()
                .id(themengebiet.getId())
                .name(themengebiet.getName())
                .fachId(fach.get().getId())
                .build();
    }

    public String deleteThemengebiet(int themengebietId) {
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

        if (themengebiet.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        themengebietRepository.delete(themengebiet.get());
        return "Erfolgreich gelöscht";
    }
}
