package com.dh.Integrador.service;
import com.dh.Integrador.dto.TurnoDTO;
import com.dh.Integrador.entity.Domicilio;
import com.dh.Integrador.entity.Odontologo;
import com.dh.Integrador.entity.Paciente;
import com.dh.Integrador.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurnoTest(){
        Odontologo odontologoAGuardar = new Odontologo("ABC123", "Lalo", "Landa");
        Odontologo odontologoAGuardar2 = new Odontologo("ABC123", "Tulio", "Tribiño");
        Odontologo odontologoGuardado2 = odontologoService.guardarOdontologo(odontologoAGuardar2);
        Paciente pacienteAGuardar = new Paciente("Javi", "Paredes", "5234", LocalDate.of(2022,11,28), new Domicilio("Calle falsa", 135,"Springfield", "Ohio"), "prueba@gmail.com");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        TurnoDTO turnoAGuardar = new TurnoDTO(LocalDate.of(2022,11,10),pacienteGuardado.getId(), odontologoGuardado.getId());
        TurnoDTO turnoGuardado = turnoService.guardarTurno(turnoAGuardar);
        Integer idEsperado = 1;
        assertEquals( idEsperado, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Integer idABuscar= 1;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idABuscar);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarTodosLosTurnosTest(){
        List<TurnoDTO> turnos = turnoService.buscarTurnos();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, turnos.size());
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        TurnoDTO turnoAActualizar = new TurnoDTO(1, LocalDate.of(2022,11,10),1,2);
        turnoService.actualizarTurno(turnoAActualizar);
        Optional<TurnoDTO> turnoActualizado = turnoService.buscarTurno(turnoAActualizar.getId());
        assertEquals(2, turnoActualizado.get().getOdontologoId());
    }

    @Test
    @Order(5)
    public void borrarTurnoTest(){
        Integer idAEliminar = 1;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }
}
