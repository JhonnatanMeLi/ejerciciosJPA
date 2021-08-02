package com.example.muelitas.service.implementation;

import com.example.muelitas.dtos.PacientDTO;
import com.example.muelitas.repository.PacientRepository;
import com.example.muelitas.service.IPacientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacientService implements IPacientService {

    private PacientRepository pacientRepository;

    private ObjectMapper objectMapper;

    public PacientService(PacientRepository pacientRepository, ObjectMapper objectMapper) {
        this.pacientRepository = pacientRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean exist(Long id) {
        return pacientRepository.existsById(id);
    }

    @Override
    public PacientDTO findById(Long id) {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.convertValue(pacientRepository.findById(id).get(), PacientDTO.class);
    }
}
