package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.ThemengebietDTOs.Request_ThemengebetDTO;
import com.example.examcrud.dto.ThemengebietDTOs.Response_ThemengebietDTO;
import com.example.examcrud.dto.ThemengebietDTOs.ThemengebietDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
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

    public List<ThemengebietDTO> getAllThemengebiete(){
        List<Themengebiet> themengebietList = themengebietRepository.findAll();
        List<ThemengebietDTO> tgList = new ArrayList<>();

        for (Themengebiet tg: themengebietList) {
            tgList.add(ThemengebietDTO.builder().id(tg.getId()).name(tg.getName()).build());
        }
        return tgList;
    }

    public Response_ThemengebietDTO getSingleThemengebiet(int themengebietId){
        Optional<Themengebiet> themengebietOptional = themengebietRepository.findById(themengebietId);
        List<FrageDTO> frageDTOList = new ArrayList<>();

        if (themengebietOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Themengebiet themengebiet = themengebietOptional.get();

        List<Frage> frageList = themengebiet.getFragenListe();

        for (Frage frage: frageList) {
            frageDTOList.add(FrageDTO.builder()
                            .id(frage.getId())
                            .name(frage.getName())
                    .build());

        }

        return Response_ThemengebietDTO.builder()
                .id(themengebiet.getId())
                .name(themengebiet.getName())
                .fragenListe(frageDTOList)
                .build();
    }

    public Response_ThemengebietDTO addNewThemengebiet(Request_ThemengebetDTO requestThemengebetDTO) {
        Themengebiet themengebiet = Themengebiet.builder()
                .id(requestThemengebetDTO.getId())
                .name(requestThemengebetDTO.getName())
//                .fragenListe(requestThemengebetDTO.getFragenListe())
//                .fach(requestThemengebetDTO.getFachId())
                .build();
        themengebietRepository.save(themengebiet);

        return Response_ThemengebietDTO.builder()
                .id(themengebiet.getId())
                .name(themengebiet.getName())
//                .fragenListe(themengebiet.getFragenListe())
//                .fachId(themengebiet.getFach().getId())
                .build();
    }
}
