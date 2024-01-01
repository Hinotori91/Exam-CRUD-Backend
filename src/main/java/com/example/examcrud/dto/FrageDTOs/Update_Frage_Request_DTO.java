package com.example.examcrud.dto.FrageDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Update_Frage_Request_DTO {
    private String name;
    private boolean examMode;

}
