package com.example.muelitas.service;

import com.example.muelitas.dtos.DoctorDTO;

public interface IDoctorService {

    boolean exist(Long id);

    DoctorDTO findById(Long id);

}
