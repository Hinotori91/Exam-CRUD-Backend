package com.example.examcrud.service;

import com.example.examcrud.dto.KalenderDTOs.KalenderDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderMillisDTO;
import com.example.examcrud.dto.KalenderDTOs.KalenderRequestDTO;
import com.example.examcrud.entity.Kalender;
import com.example.examcrud.repository.Kalender_Repository;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<KalenderDTO> getAllEvents() {
        List<Kalender> eventList = kalenderRepository.findAll();
        List<KalenderDTO> responseList = new ArrayList<>();

        for (Kalender event : eventList) {
            responseList.add(KalenderDTO.builder()
                    .id(event.getId())
                    .millisekunden(event.getMillisekunden())
                    .timeStart(event.getTimeStart())
                    .timeEnd(event.getTimeEnd())
                    .event(event.getEvent())
                    .kategorie(event.getKategorie())
                    .build());
        }
        return responseList;
    }

    public String deleteEvent(int eventId) {
        Optional<Kalender> event = kalenderRepository.findById(eventId);

        if (event.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        kalenderRepository.delete(event.get());
        return "Erfolgreich gelöscht!";
    }

    public String downloadKalender() throws IOException {
        List<Kalender> eventList = kalenderRepository.findAll();
        int uid = 0;

        Calendar calendar = new Calendar();
        for (Kalender event : eventList) {
            Date date = new Date(event.getMillisekunden());
            DateFormat format = new SimpleDateFormat("yyyyMMdd");

            String stundeBeginn = event.getTimeStart().substring(0, 2);
            String minuteBeginn = event.getTimeStart().substring(3);

            String stundeEnde = event.getTimeEnd().substring(0, 2);
            String minuteEnde = event.getTimeEnd().substring(3);

            try {
                VEvent vEvent = new VEvent(
                        new DateTime(format.format(date) + "T" + stundeBeginn + minuteBeginn + "00Z"),
                        new DateTime(format.format(date) + "T" + stundeEnde + minuteEnde + "00Z"),
                        event.getEvent()
                );

                vEvent.getProperties().add(Version.VERSION_2_0);
                vEvent.getProperties().add(new ProdId(uid + ""));
                vEvent.getProperties().add(new Description("LaMa - Learn and Management App"));
                vEvent.getProperties().add(Method.PUBLISH);

                calendar.getComponents().add(vEvent);
                uid++;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return calendar.toString();
    }
}
