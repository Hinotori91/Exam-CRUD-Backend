package com.example.examcrud.repository;

import com.example.examcrud.entity.Antwort;
import com.example.examcrud.entity.Frage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Antwort_Repository extends JpaRepository<Antwort, Integer> {
    List<Antwort> findByFrage(Frage frage);
}
