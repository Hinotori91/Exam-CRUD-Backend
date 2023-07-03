package com.example.examcrud.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemengebietDTO {
    private int id;
    private String name;
}
