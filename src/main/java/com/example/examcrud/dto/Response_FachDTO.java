package com.example.examcrud.dto;

import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response_FachDTO {
    private int id;
    private String name;
    private List<FrageDTO> frageListe;
    private List<ThemengebietDTO> themengebiet;
}
