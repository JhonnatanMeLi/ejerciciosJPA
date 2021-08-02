package com.example.muelitas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "pacient_id", nullable = false)
    private Long pacientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    private LocalDateTime date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pacient_id", referencedColumnName="id", insertable = false, updatable = false)
    private Pacient pacient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName="id", insertable = false, updatable = false)
    private Doctor doctor;
}
