package com.example.examcrud.repository;

import com.example.examcrud.entity.Antwort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Antwort_Repository extends JpaRepository<Antwort, Integer> {
}
