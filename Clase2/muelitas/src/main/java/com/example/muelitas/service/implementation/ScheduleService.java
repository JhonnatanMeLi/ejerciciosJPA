package com.example.muelitas.service.implementation;

import com.example.muelitas.dtos.DoctorDTO;
import com.example.muelitas.model.Doctor;
import com.example.muelitas.model.Pacient;
import com.example.muelitas.model.Schedule;
import com.example.muelitas.repository.ScheduleRepository;
import com.example.muelitas.service.IScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    private DoctorService doctorService;
    private PacientService pacientService;
    private ScheduleRepository scheduleRepository;
    private ObjectMapper objectMapper;

    public ScheduleService(DoctorService doctorService, PacientService pacientService, ScheduleRepository scheduleRepository, ObjectMapper objectMapper) {
        this.doctorService = doctorService;
        this.pacientService = pacientService;
        this.scheduleRepository = scheduleRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public String agendarCita(Long pacienteId, Long doctorId) {
        String response = validateIds(pacienteId, doctorId);
        LocalDateTime fechaCita;
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        if (response.equals("")) {
            List<Schedule> agendaDoctor = scheduleRepository. findByDoctorIdOrderByDateDesc(doctorId);
            if (agendaDoctor.isEmpty())
                fechaCita = LocalDateTime.now();
            else
                fechaCita = agendaDoctor.get(0).getDate().plusMinutes(30);

            Schedule entity = new Schedule();
            entity.setDate(fechaCita);
            DoctorDTO x = doctorService.findById(doctorId);
            entity.setDoctorId(x.getId());
            entity.setPacientId(pacientService.findById(pacienteId).getId());
            scheduleRepository.save(entity);
            response = "Se ha agendado la cita para el " + fechaCita.toString();
        }
        return response;
    }

    private String validateIds(Long pacienteId, Long doctorId) {
        String response = "";
        if (!pacientService.exist(pacienteId)) {
            response = response + "No existe el paciente con el id: " + pacienteId + " \n";
        }
        if (!doctorService.exist(doctorId)){
            response = response + "No existe el doctor con el id: " + doctorId + " \n";
        }
        return response;
    }
}
