package com.example.examcrud.repository;

import com.example.examcrud.entity.Fach;
import com.example.examcrud.entity.Frage;
import com.example.examcrud.entity.Themengebiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Frage_Repository extends JpaRepository<Frage, Integer> {
	List<Frage> findByThemengebiet(Themengebiet themengebiet);

	List<Frage> findByFaecher(Fach fach);

	int countByThemengebiet(Themengebiet themengebiet);
}
