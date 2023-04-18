package com.dh.Integrador.service;


import com.dh.Integrador.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest(){
        Odontologo odontologoAGuardar = new Odontologo("ABC123", "Lalo", "Landa");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        Integer idEsperado = 1;
        assertEquals( idEsperado, odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Integer idABuscar= 1;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(idABuscar);
        assertNotNull(odontologoBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarTodosLosOdontologosTest(){
        List<Odontologo> odontologos = odontologoService.listarOdontologos();

        System.out.println(odontologos);
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, odontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo odontologoAActualizar = new Odontologo("123ABC","Tulio","Tribiño");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        Optional<Odontologo> odontologoActualizado = odontologoService.buscarOdontologoXId(odontologoAActualizar.getId());
        assertEquals("Tulio", odontologoActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void borrarOdontologoTest(){
        Integer idAEliminar = 1;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoXId(idAEliminar);
        assertFalse(odontologoEliminado.isPresent());
    }

}
