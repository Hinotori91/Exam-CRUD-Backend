package com.example.examcrud.repository;

import com.example.examcrud.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDo_Repository extends JpaRepository<ToDo, Integer> {
}
