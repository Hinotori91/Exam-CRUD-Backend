package com.example.examcrud.repository;

import com.example.examcrud.dto.FrageDTO;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Frage_Repository extends JpaRepository<Frage, Integer> {
    List<FrageDTO> findByThemengebiet(Themengebiet themengebiet);
}
