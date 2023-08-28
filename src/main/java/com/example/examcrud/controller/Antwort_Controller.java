package com.example.examcrud.controller;

import com.example.examcrud.service.Antwort_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/antwort")
public class Antwort_Controller {
    @Autowired
    Antwort_Service antwortService;

    
}
