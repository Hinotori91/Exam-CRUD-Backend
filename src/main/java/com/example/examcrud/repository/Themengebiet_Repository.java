package com.example.examcrud.repository;

import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Themengebiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Themengebiet_Repository extends JpaRepository<Themengebiet, Integer> {
    List<Themengebiet> findByFach(Fach fach);

}
