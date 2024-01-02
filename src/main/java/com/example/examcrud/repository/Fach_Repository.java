package com.example.examcrud.repository;

import com.example.examcrud.entity.Fach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fach_Repository extends JpaRepository<Fach, Integer> {
}
