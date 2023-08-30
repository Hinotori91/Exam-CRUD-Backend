package com.example.examcrud.service;

import com.example.examcrud.dto.AntwortenDTOs.Add_Antwort_Request_DTO;
import com.example.examcrud.dto.AntwortenDTOs.Add_Antwort_Response_DTO;
import com.example.examcrud.dto.AntwortenDTOs.AntwortDTO;
import com.example.examcrud.entity.Antwort;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.repository.Antwort_Repository;
import com.example.examcrud.repository.Frage_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Antwort_Service {
    @Autowired
    Antwort_Repository antwortRepository;
    @Autowired
    Frage_Repository frageRepository;


    public List<AntwortDTO> getAllAntwortenFromFrage(int frageId) {
        List<AntwortDTO> antwortDTOList = new ArrayList<>();
        Optional<Frage> frage = frageRepository.findById(frageId);

        if (frage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Antwort> antwortList = antwortRepository.findByFrage(frage.get());

        for (Antwort antwort : antwortList) {
            antwortDTOList.add(AntwortDTO.builder()
                    .id(antwort.getId())
                    .name(antwort.getName())
                    .build());
        }
        return antwortDTOList;
    }

    public Add_Antwort_Response_DTO addAntwortToFrage(Add_Antwort_Request_DTO addAntwortRequestDto) {
    Optional<Frage> frage = frageRepository.findById(addAntwortRequestDto.getFrageId());

    if(frage.isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Antwort antwort = Antwort.builder()
            .name(addAntwortRequestDto.getName())
            .richtig(addAntwortRequestDto.getRichtig())
            .frage(frage.get())
            .build();

    antwortRepository.save(antwort);

    return Add_Antwort_Response_DTO.builder()
            .id(antwort.getId())
            .name(antwort.getName())
            .build();
    }
}
