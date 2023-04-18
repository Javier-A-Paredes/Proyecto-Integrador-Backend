package com.dh.Integrador.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //MN8452
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //@OneToMany(mappedBy = "odontologo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnoSet = new HashSet<>();

    public Odontologo(){

    }
    public Odontologo(Integer id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Set<Turno> getTurnoSet() {
        return turnoSet;
    }

    public void setTurnoSet(Set<Turno> turnoSet) {
        this.turnoSet = turnoSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
