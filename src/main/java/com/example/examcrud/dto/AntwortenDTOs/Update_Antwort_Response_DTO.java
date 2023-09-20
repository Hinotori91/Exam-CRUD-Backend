package com.example.examcrud.dto.AntwortenDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Update_Antwort_Response_DTO {
    private int id;
    private String name;
    private boolean richtig;
}
