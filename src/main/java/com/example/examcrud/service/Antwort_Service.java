package com.example.examcrud.service;

import com.example.examcrud.repository.Antwort_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Antwort_Service {
    @Autowired
    Antwort_Repository antwortRepository;


}
