package com.example.examcrud.repository;

import com.example.examcrud.entity.Kalender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Kalender_Repository extends JpaRepository<Kalender, Integer> {
	List<Kalender> findByMillisekunden(long millisekunden);

}
