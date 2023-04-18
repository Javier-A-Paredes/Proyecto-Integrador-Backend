package com.dh.Integrador.service;

import com.dh.Integrador.entity.Odontologo;
import com.dh.Integrador.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository){ this.odontologoRepository = odontologoRepository; }
    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Listando todos los odontologos");
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarOdontologoXId(Integer id){
        LOGGER.info("Buscando odontologo por id: " + id);
        return odontologoRepository.findById(id);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Odontologo guardarOdontologo(Odontologo odontologo){
        LOGGER.info("Guardando odontologo: " + odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Actualizando Odontologo con id: " +odontologo.getId());
        return odontologoRepository.save(odontologo);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eliminarOdontologo(Integer id){
        LOGGER.info("");
        odontologoRepository.deleteById(id);
    }

}
