package com.example.examcrud.controller;

import com.example.examcrud.dto.ToDo.Todo_Request_DTO;
import com.example.examcrud.dto.ToDo.Todo_Response_DTO;
import com.example.examcrud.service.Todo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class ToDo_Controller {
	@Autowired
	Todo_Service todoService;

	@PostMapping
	public ResponseEntity<?> addNewTodo(@RequestBody Todo_Request_DTO todoRequestDto) {
		Todo_Response_DTO todoResponseDto;
		try {
			todoResponseDto = todoService.addNewTodo(todoRequestDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllTasks() {
		List<Todo_Response_DTO> todoResponseDto;
		try {
			todoResponseDto = todoService.getAllTaks();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody Todo_Request_DTO todoRequestDto, @PathVariable int taskId) {
		Todo_Response_DTO response;
		try {
			response = todoService.updateTask(todoRequestDto, taskId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteEvent(@PathVariable int taskId) {
		String message;
		try {
			message = todoService.deleteTask(taskId);
		} catch (Exception e) {
			return new ResponseEntity<>("Kein Eintrag mit dieser ID gefunden", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
