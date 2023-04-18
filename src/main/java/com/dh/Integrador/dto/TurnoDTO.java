package com.dh.Integrador.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Integer id;
    private LocalDate fecha;
    private Integer pacienteId;
    private Integer odontologoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Integer odontologoId) {
        this.odontologoId = odontologoId;
    }

    public TurnoDTO(){

    }
    public TurnoDTO(Integer id, LocalDate fecha, Integer pacienteId, Integer odontologoId) {
        this.id = id;
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public TurnoDTO(LocalDate fecha, Integer pacienteId, Integer odontologoId) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", pacienteId=" + pacienteId +
                ", odontologoId=" + odontologoId +
                '}';
    }
}
