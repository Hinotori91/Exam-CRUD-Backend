package com.example.examcrud.service;

import com.example.examcrud.dto.ToDo.Todo_Request_DTO;
import com.example.examcrud.dto.ToDo.Todo_Response_DTO;
import com.example.examcrud.entity.ToDo;
import com.example.examcrud.repository.ToDo_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Todo_Service {
    @Autowired
    ToDo_Repository todoRepository;


    public Todo_Response_DTO addNewTodo(Todo_Request_DTO todoRequestDto) {
        ToDo todo = ToDo.builder()
                .name(todoRequestDto.getTask())
                .checked(todoRequestDto.getChecked())
                .build();
        todoRepository.save(todo);

        return Todo_Response_DTO.builder()
                .id(todo.getId())
                .task(todo.getName())
                .checked(todo.getChecked())
                .build();
    }

    public List<Todo_Response_DTO> getAllTaks() {
        List<ToDo> todoList = todoRepository.findAll();
        List<Todo_Response_DTO> responseList = new ArrayList<>();

        for (ToDo element : todoList) {
            responseList.add(Todo_Response_DTO.builder()
                    .id(element.getId())
                    .task(element.getName())
                    .checked(element.getChecked())
                    .build());
        }
        return responseList;
    }

    public Todo_Response_DTO updateTask(Todo_Request_DTO todoRequestDto, int taskId) {
        Optional<ToDo> task = todoRepository.findById(taskId);

        if (task.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        task.get().setName(todoRequestDto.getTask());
        task.get().setChecked(todoRequestDto.getChecked());
        todoRepository.save(task.get());

        return Todo_Response_DTO.builder()
                .id(task.get().getId())
                .task(task.get().getName())
                .checked(task.get().getChecked())
                .build();
    }
}
