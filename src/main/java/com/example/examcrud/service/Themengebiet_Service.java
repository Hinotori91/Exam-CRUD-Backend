package com.example.examcrud.service;

import com.example.examcrud.dto.ThemengebietDTO;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Themengebiet_Service {
    @Autowired
    Themengebiet_Repository themengebietRepository;

    public List<ThemengebietDTO> getAllThemengebiete(){
        List<Themengebiet> themengebietList = themengebietRepository.findAll();
        List<ThemengebietDTO> tgList = new ArrayList<>();

        for (Themengebiet tg: themengebietList) {
            tgList.add(ThemengebietDTO.builder().id(tg.getId()).name(tg.getName()).build());
        }
        return tgList;
    }

    public Optional<Themengebiet> getSingleThemengebiet(int themengebietId){
        return themengebietRepository.findById(themengebietId);
    }
}
