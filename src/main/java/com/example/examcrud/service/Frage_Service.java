package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Frage_Repository;
import com.example.examcrud.repository.Themengebiet_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        List<FrageDTO> frageDTOList = null;
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);

        if (themengebiet.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Frage> frageList = frageRepository.findByThemengebiet(themengebiet.get());

        for (Frage frage : frageList){
            frageDTOList.add(FrageDTO.builder()
                            .id(frage.getId())
                            .name(frage.getName())
                            .faecherId(frage.getFaecher().getId())
                            .themengebietId(frage.getThemengebiet().getId())
//                            .antwortListe(frage.getAntwortListe())
                    .build());
        }

        return frageDTOList;
    }
}