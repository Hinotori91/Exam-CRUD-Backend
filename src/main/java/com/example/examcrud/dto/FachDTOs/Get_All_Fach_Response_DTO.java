package com.example.examcrud.dto.FachDTOs;

import com.example.examcrud.dto.ThemengebietDTOs.ThemengebietDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Get_All_Fach_Response_DTO {
    private int id;
    private String name;
}

