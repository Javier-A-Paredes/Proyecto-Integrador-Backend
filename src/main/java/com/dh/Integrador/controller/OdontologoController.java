package com.dh.Integrador.controller;

import com.dh.Integrador.entity.Odontologo;
import com.dh.Integrador.exception.BadRequestException;
import com.dh.Integrador.exception.ResourceNotFoundException;
import com.dh.Integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//rest o no
@CrossOrigin("*")
@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorID(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado != null) {
            return ResponseEntity.ok(odontologoBuscado);
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo con id: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(odontologo.getId());
        if(odontologoBuscado != null){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó el odontologo con id: " +odontologo.getId());
        } else {
            throw new BadRequestException("No se encontró el odontologo con id: " + odontologo.getId());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) throws BadRequestException{
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado != null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se eliminó el odontologo con id: " + id);
        } else {
            throw new BadRequestException("No se encontró el odontologo con id: " + id);
        }
    }
}
