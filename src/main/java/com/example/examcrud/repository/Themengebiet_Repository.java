package com.example.examcrud.repository;

import com.example.examcrud.entity.Themengebiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Themengebiet_Repository extends JpaRepository<Themengebiet, Integer> {
}
