package com.example.examcrud.repository;

import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Fach_Themengebiet;
import com.example.examcrud.entity.Fach_Themengebiet_PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Fach_Themengebiet_Repository extends JpaRepository<Fach_Themengebiet, Integer> {
}
