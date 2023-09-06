package com.example.examcrud.dto.ToDo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo_Request_DTO {
    private String task;
    private Boolean checked;
}
