package com.example.examcrud.dto.ToDo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo_Response_DTO {
    private int id;
    private String task;
    private Boolean checked;
}
