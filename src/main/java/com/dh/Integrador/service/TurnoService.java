package com.dh.Integrador.service;

import com.dh.Integrador.dto.TurnoDTO;
import com.dh.Integrador.entity.Odontologo;
import com.dh.Integrador.entity.Paciente;
import com.dh.Integrador.entity.Turno;
import com.dh.Integrador.repository.OdontologoRepository;
import com.dh.Integrador.repository.PacienteRepository;
import com.dh.Integrador.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;
    private TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, TurnoRepository turnoRepository){
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO guardarTurno(TurnoDTO turno){
        Turno turnoAGuardar = turnoDTOaTurno(turno);
        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        LOGGER.info("Se guardo un turno para la fecha: " + turno.getFecha());
        return turnoATurnoDTO(turnoGuardado);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eliminarTurno(Integer id){
        LOGGER.info("Se eliminó el turno con id: " + id);
        turnoRepository.deleteById(id);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void actualizarTurno(TurnoDTO turno){
        LOGGER.info("Se actualizó el turno con id: " + turno.getId());
        Turno turnoAActualizar = turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }
    public Optional<TurnoDTO> buscarTurno(Integer id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            //turno encontrado
            LOGGER.info("Se encontró el turno con id: " + id);
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            LOGGER.info("No se encontró el turno con id: " + id);
            //no se encuentra el turno
            return Optional.empty();
        }
    }
    public List<TurnoDTO> buscarTurnos (){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));
        }
        LOGGER.info("Buscando todos los turnos...");
        return respuesta;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir ese turno en un turno DTO
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        System.out.println("TURNODTO RECIBIDO: " + turnoDTO);
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        //cargar los elementos
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        System.out.println(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        //asociar cada elemento
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        //salida
        return turno;
    }

}
