package com.dh.Integrador.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id",referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id",referencedColumnName = "id")
    private Odontologo odontologo;
    @Column
    private LocalDate fecha;

    public Turno(){

    }

    public Turno( Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Odontologo getOdontologo() {
        return odontologo;
    }
    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
