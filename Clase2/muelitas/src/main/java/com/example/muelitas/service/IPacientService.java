package com.example.muelitas.service;

import com.example.muelitas.dtos.PacientDTO;

public interface IPacientService {

    boolean exist(Long id);

    PacientDTO findById(Long id);

}
