package com.example.examcrud.dto.AntwortenDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntwortDTO {
    private int id;
    private String name;
    private Boolean richtig;
}
