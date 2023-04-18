package com.dh.Integrador.service;

import com.dh.Integrador.entity.Paciente;
import com.dh.Integrador.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository){ this.pacienteRepository = pacienteRepository; }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Paciente guardarPaciente(Paciente paciente){
        LOGGER.info("Se guardó el paciente: " + paciente.getNombre());
        return pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarTodosLosPacientes(){
        LOGGER.info("Se buscaron todos los pacientes");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacientePorId(Integer id){
        LOGGER.info("Buscando paciente con id: " + id);
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPacienteXEmail(String email){
        LOGGER.info("Buscando paciente con email: " + email);
        return pacienteRepository.findByEmail(email);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void actualizarPaciente(Paciente paciente){
        LOGGER.info("Se actualizó el paciente con id: " + paciente.getId());
        pacienteRepository.save(paciente);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void borrarpaciente(Integer id){
        LOGGER.info("Se eliminó el paciente con id: " + id);
        pacienteRepository.deleteById(id);
    }

}
