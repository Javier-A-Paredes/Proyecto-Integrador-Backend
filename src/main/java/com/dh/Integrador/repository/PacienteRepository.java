package com.dh.Integrador.repository;

import com.dh.Integrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByEmail(String email);
}
