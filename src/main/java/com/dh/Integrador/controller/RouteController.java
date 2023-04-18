package com.dh.Integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {


    @GetMapping
        (value = { "/", "/pacientes/**", "/listar-pacientes", "/odontologos/**", "/listar-odontologos" ,"/turnos/**","/listar-turnos"})

    public String welcome() {
        return "index";
    }

}