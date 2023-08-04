package com.example.examcrud.dto.ThemengebietDTOs;

import com.example.examcrud.dto.FrageDTOs.FrageDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Get_One_Detailed_Themengebiet_DTO {
    private int id;
    private String name;
    private int fachId;
    private List<FrageDTO> frageDTOList;
}
