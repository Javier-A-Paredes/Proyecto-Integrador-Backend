//package com.dh.Integrador.security;
//
//import com.dh.Integrador.entity.Usuario;
//import com.dh.Integrador.entity.UsuarioRole;
//import com.dh.Integrador.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CargarDatosIniciales implements ApplicationRunner {
//    private UsuarioRepository usuarioRepository;
//    @Autowired
//    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        //cargar un usuario para probar
//        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
//        String passCifrada = cifrador.encode("1234");
//        Usuario usuario = new Usuario("Usuario","powerless","prueba@gmail.com", passCifrada, UsuarioRole.ROLE_USER);
//        usuarioRepository.save(usuario);
//        Usuario admin = new Usuario("Admin", "Poderoso", "admin@gmail.com", passCifrada,UsuarioRole.ROLE_ADMIN);
//        usuarioRepository.save(admin);
//    }
//}
