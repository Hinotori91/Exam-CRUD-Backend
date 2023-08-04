package com.example.examcrud.dto.ThemengebietDTOs;

import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Get_All_Themengebiet_Response_DTO {
    private int id;
    private String name;
    private int fachId;
}
