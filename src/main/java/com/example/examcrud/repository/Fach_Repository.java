package com.example.examcrud.repository;

import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Frage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Fach_Repository extends JpaRepository<Fach, Integer> {
}
