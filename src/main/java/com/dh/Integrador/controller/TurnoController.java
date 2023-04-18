package com.dh.Integrador.controller;

import com.dh.Integrador.dto.TurnoDTO;
import com.dh.Integrador.entity.Odontologo;
import com.dh.Integrador.entity.Paciente;
import com.dh.Integrador.entity.Turno;
import com.dh.Integrador.exception.BadRequestException;
import com.dh.Integrador.exception.ResourceNotFoundException;
import com.dh.Integrador.service.OdontologoService;
import com.dh.Integrador.service.PacienteService;
import com.dh.Integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TurnoDTO>> listarLosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTurnos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        //tengo dos alternativas.
        Optional<TurnoDTO> turnoBuscado=turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoDTO> registarTurno(@RequestBody TurnoDTO turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPacienteId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologoId());
        System.out.println(turno);
        ResponseEntity<TurnoDTO> respuesta;
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
//
            turno.setOdontologoId(odontologoBuscado.get().getId());
            turno.setPacienteId(pacienteBuscado.get().getId());

            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            //uno o ambos no existen, debemos bloquear la operación
            respuesta=ResponseEntity.badRequest().build();
            //alternativa para seleccionar cualquier código
            //respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }
    @PutMapping
    ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        ResponseEntity<String> response;
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            response = ResponseEntity.ok("Se actualizó el turno con ID: " + turno.getId());
        } else {
//            response = ResponseEntity.badRequest().body("No se encontró el turno con ID: " + turno.getId());
            throw new BadRequestException("No se encontró el turno con ID: " + turno.getId());
        }
        return response;
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> eliminarTurno(@PathVariable Integer id) throws BadRequestException{
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        ResponseEntity<String> response;
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok("Se eliminó el turno con ID: " + turnoBuscado.get().getId());
        } else {
//            response = ResponseEntity.badRequest().body("No se encontró el turno con ID: " + turnoBuscado.get().getId());
            throw new BadRequestException("No se encontró el turno con ID: " + turnoBuscado.get().getId());
        }
        return response;
    }
}
