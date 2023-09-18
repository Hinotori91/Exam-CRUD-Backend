package com.example.examcrud.service;

import com.example.examcrud.dto.KalenderDTOs.KalenderDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderMillisDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderRequestDTO;
import com.example.examcrud.entity.Kalender;
import com.example.examcrud.repository.Kalender_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Kalender_Service {
    @Autowired
    Kalender_Repository kalenderRepository;

    public KalenderDTO addNewEvent(KalenderRequestDTO kalenderRequestDTO) {
        Kalender event = Kalender.builder()
                .millisekunden(kalenderRequestDTO.getMillisekunden())
                .timeStart(kalenderRequestDTO.getTimeStart())
                .timeEnd(kalenderRequestDTO.getTimeEnd())
                .event(kalenderRequestDTO.getEvent())
                .kategorie(kalenderRequestDTO.getKategorie())
                .build();

        kalenderRepository.save(event);

        return KalenderDTO.builder()
                .id(event.getId())
                .millisekunden(event.getMillisekunden())
                .timeStart(event.getTimeStart())
                .timeEnd(event.getTimeEnd())
                .event(event.getEvent())
                .kategorie(event.getKategorie())
                .build();
    }

    public List<KalenderDTO> getEventsbyDate(KalenderMillisDTO kalenderMillisDTO) {
        List<Kalender> eventList = kalenderRepository.findByMillisekunden(kalenderMillisDTO.getMillisekunden());
        List<KalenderDTO> responseEventList = new ArrayList<>();

        for (Kalender event : eventList) {
            responseEventList.add(KalenderDTO.builder()
                    .id(event.getId())
                    .millisekunden(event.getMillisekunden())
                    .timeStart(event.getTimeStart())
                    .timeEnd(event.getTimeEnd())
                    .event(event.getEvent())
                    .kategorie(event.getKategorie())
                    .build());
        }
        return responseEventList;
    }

    public String deleteEvent(int eventId) {
        Optional<Kalender> event = kalenderRepository.findById(eventId);

        if (event.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        kalenderRepository.delete(event.get());
        return "Erfolgreich gelöscht!";
    }
}
