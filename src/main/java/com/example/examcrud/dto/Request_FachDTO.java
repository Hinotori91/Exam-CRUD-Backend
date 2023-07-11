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
public class Request_FachDTO {
    private int id;
    private String name;
//    private List<String> frageListe;
//    private Themengebiet themengebiet;
}
