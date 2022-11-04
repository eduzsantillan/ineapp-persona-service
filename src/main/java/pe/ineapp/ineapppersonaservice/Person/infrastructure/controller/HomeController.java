package pe.ineapp.ineapppersonaservice.Person.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/public")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok("Bienvenido a la API de personas");
    }



    @GetMapping("/private")
    public ResponseEntity<?> getPrivate(){
        return ResponseEntity.ok("Entradas a precio rebajado");
    }

}
