package com.example.examcrud.dto.FachDTOs;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import com.example.examcrud.dto.ThemengebietDTOs.ThemengebietDTO;
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
}
