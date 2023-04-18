package com.dh.Integrador.service;

import com.dh.Integrador.entity.Domicilio;
import com.dh.Integrador.entity.Paciente;
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
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("Javi", "Paredes", "5234", LocalDate.of(2022,11,28), new Domicilio("Calle falsa", 135,"Springfield", "Ohio"), "prueba@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        Integer idEsperado = 1;
        System.out.println(pacienteService.buscarTodosLosPacientes().size());
        assertEquals( idEsperado, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Integer idABuscar= 1;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarTodosLosPacientesTest(){
        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();
        //preguntar por la cantidad de pacientes
        System.out.println(pacientes);
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar = new Paciente(1,"Javier","Paredes", "5234",LocalDate.of(2022,11,28),
                new Domicilio("Calle falsa", 135,"Springfield", "Ohio"), "prueba@gmail.com" );
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPacientePorId(pacienteAActualizar.getId());
        assertEquals("Javier", pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void buscarPacientePorEmailTest(){
        String emailBuscado = "prueba@gmail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXEmail(emailBuscado);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(6)
    public void borrarPacienteTest(){
        Integer idAEliminar = 1;
        pacienteService.borrarpaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPacientePorId(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }




}
