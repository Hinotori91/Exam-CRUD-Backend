package com.example.examcrud.dto.AntwortenDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_Antwort_Response_DTO {
    private int id;
    private String name;
    private Boolean richtig;
}
