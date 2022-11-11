package pe.ineapp.ineapppersonaservice.Person.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

    @RequestMapping("")
    public String Index(@RequestParam(defaultValue = "Invitado") String param){
        return "Bienvenido ".concat(param);
    }
}
