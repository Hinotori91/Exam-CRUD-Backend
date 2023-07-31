package com.example.examcrud.dto;

import com.example.examcrud.entity.Frage;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response_ThemengebietDTO {
    private int id;
    private String name;
    private List<FrageDTO> fragenListe;
    private int fachId;
}
