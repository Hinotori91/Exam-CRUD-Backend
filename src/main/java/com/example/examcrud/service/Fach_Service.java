package com.example.examcrud.service;

import com.example.examcrud.dto.FrageDTO;
import com.example.examcrud.dto.Request_FachDTO;
import com.example.examcrud.dto.Response_FachDTO;
import com.example.examcrud.dto.ThemengebietDTO;
import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Fach_Themengebiet;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import com.example.examcrud.repository.Fach_Repository;
import com.example.examcrud.repository.Fach_Themengebiet_Repository;
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
public class Fach_Service {
    @Autowired
    Fach_Repository fachRepository;
    @Autowired
    Themengebiet_Repository themengebietRepository;

//    @Autowired
//    Fach_Themengebiet_Repository fachThemengebietRepository;
    @Autowired
    Frage_Repository frageRepository;

    public Response_FachDTO addNewFach(Request_FachDTO requestFachDTO) {
        Fach fach = Fach.builder().name(requestFachDTO.getName()).build();
        fachRepository.save(fach);

        return Response_FachDTO.builder()
                .id(fach.getId())
                .name(fach.getName())
                .build();
    }

    public List<Response_FachDTO> getAllFaecher() {
        List<Fach> fachList = fachRepository.findAll();
        List<Response_FachDTO> responseList = new ArrayList<>();
        List<FrageDTO> frageDTOList = new ArrayList<>();
        List<Frage> frageList = frageRepository.findAll(); // TODO: findAllByFachId!!
        List<Themengebiet> themengebietList = themengebietRepository.findAll(); // TODO: findAllByFachId
        List<ThemengebietDTO> themengebietDTOList = new ArrayList<>();

        for (Fach element : fachList) {
                     for (Frage frage:frageList){
                FrageDTO frageDTO = FrageDTO.builder()
                        .id(frage.getId())
                        .name(frage.getName())
                        .faecherId(element.getId())
                        .themengebietId(themengebietList.get(0).getId()) // TODO: wird geändert wenn ich die richtige ID auslesen kann
                        .build();
                frageDTOList.add(frageDTO);
            }

            for (Themengebiet tg:themengebietList) {
                ThemengebietDTO tgdto = ThemengebietDTO.builder()
                        .id(tg.getId())
                        .name(tg.getName())
                        .build();
                themengebietDTOList.add(tgdto);
            }


            responseList.add(Response_FachDTO.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .frageListe(frageDTOList)
                    .themengebiet(themengebietDTOList)
                    .build());
        }
        return responseList;
    }

    public Response_FachDTO getOneFach(int fachId) {
        Optional<Fach> fach = fachRepository.findById(fachId);

        if (fach.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            Fach fach1 = fach.get();
            return Response_FachDTO.builder()
                    .id(fach1.getId())
                    .name(fach1.getName())
//                    .themengebiet(fach1.getThemengebietList())
//                    .frageListe(fach1.getFrageListe())
                    .build();
        }
    }

    public Response_FachDTO connectThemengebietToFach(int fachId, int themengebietId) {
        Optional<Fach> fach = fachRepository.findById(fachId);
        Optional<Themengebiet> themengebiet = themengebietRepository.findById(themengebietId);
        List<ThemengebietDTO> themengebietDTOList = new ArrayList<>();

        if (fach.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            if (themengebiet.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                fach.get().getThemengebietListe().add(themengebiet.get());
                fachRepository.save(fach.get());

                ThemengebietDTO themengebietDTO = ThemengebietDTO.builder()
                        .id(themengebietId)
                        .name(themengebiet.get().getName())
                        .build();

                themengebietDTOList.add(themengebietDTO);


                Fach fach2 = fach.get();
                return Response_FachDTO.builder()
                        .id(fach2.getId())
                        .name(fach2.getName())
                        .themengebiet(themengebietDTOList)
                        .build();
            }
        }
    }
}
