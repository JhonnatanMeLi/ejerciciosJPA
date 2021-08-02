package com.example.clase1.service;

import com.example.clase1.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository stuRepo;

    public StudentService(StudentRepository stuRepo) {
        this.stuRepo = stuRepo;
    }
}
