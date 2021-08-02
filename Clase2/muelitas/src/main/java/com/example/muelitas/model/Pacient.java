package com.example.muelitas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "PACIENTS")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String dni;

    @JsonManagedReference
    @OneToMany(mappedBy = "pacient")
    private List<Schedule> appointment;

}
