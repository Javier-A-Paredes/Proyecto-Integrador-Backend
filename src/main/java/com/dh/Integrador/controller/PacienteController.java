package com.dh.Integrador.controller;

import com.dh.Integrador.entity.Paciente;
import com.dh.Integrador.exception.BadRequestException;
import com.dh.Integrador.exception.ResourceNotFoundException;
import com.dh.Integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> traerTodosLosPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> traerPacienteXId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorId(id);
        if(pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        } else {
        throw new ResourceNotFoundException("No se encontró el paciente con id: " + id);
        }
    }

    @GetMapping("/buscar-email/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPacienteXEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteXEmail(email);
        if(pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            throw new ResourceNotFoundException("No se encontró el paciente con email: " + email);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorId(paciente.getId());
        if (pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó el paciente con apellido "+paciente.getApellido());
        }
        else{
            throw new BadRequestException("El paciente con ID= "+paciente.getId()+" no existe en la BD." +
                    "No puede actualizar algo que no existe :(");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) throws BadRequestException{
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorId(id);
        if (pacienteBuscado!=null){
            pacienteService.borrarpaciente(id);
            return ResponseEntity.ok("Se eliminó al paciente con id: " + id);
        } else {
            throw new BadRequestException("No se encontró al paciente con id: " + id);
        }
    }

}
