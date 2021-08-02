package com.example.muelitas.service.implementation;

import com.example.muelitas.dtos.DoctorDTO;
import com.example.muelitas.repository.DoctorRepository;
import com.example.muelitas.service.IDoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements IDoctorService {

    private DoctorRepository doctorRepository;

    private ObjectMapper objectMapper;

    public DoctorService(DoctorRepository doctorRepository, ObjectMapper objectMapper) {
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean exist(Long id) {
        return doctorRepository.existsById(id);
    }

    @Override
    public DoctorDTO findById(Long id) {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.convertValue(doctorRepository.findById(id).get(), DoctorDTO.class);
    }
}
