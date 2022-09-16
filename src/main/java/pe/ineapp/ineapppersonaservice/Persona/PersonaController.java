package pe.ineapp.ineapppersonaservice.Persona;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonaController {

    @GetMapping
    @RequestMapping("/getAll")
    public String getPersona(){

        Persona persona = new Persona("Eduardo");
        Persona persona1 = new Persona();

        return persona.getNombre();
    }

    //PUT



    //POST



    //DELETE


}
