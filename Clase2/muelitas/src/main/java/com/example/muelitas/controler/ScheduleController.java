package com.example.muelitas.controler;

import com.example.muelitas.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendar")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @PostMapping(path = "/{pacienteId}/{doctorId}")
    public ResponseEntity<String> agendarCita(@PathVariable Long pacienteId, @PathVariable Long doctorId) {
        return ResponseEntity.ok(scheduleService.agendarCita(pacienteId, doctorId));
    }

}
