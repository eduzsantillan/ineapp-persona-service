package pe.ineapp.ineapppersonaservice.Person.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pe.ineapp.ineapppersonaservice.Person.application.service.PersonService;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.PokemonResponse;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.PokemonResponseDetail;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.UserResponse;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping({"/ep","/index","person","/"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RestTemplate restTemplate;

    private static String url="http://localhost:8080/security/user/login?username={1}&password={2}";

    private static String urlPokemon="https://pokeapi.co/api/v2/pokemon";


    @GetMapping
    @RequestMapping("/getall")
    public List<Object> getAll() {
        //return personService.getAll();



        PokemonResponse response = restTemplate.getForObject(urlPokemon
                ,PokemonResponse.class );

        return Arrays.asList(response);

    }

    @GetMapping
    @RequestMapping("/getbydni")
    public UserResponse getByDni(@RequestParam String dni) {
        return personService.getByDni(dni);
    }


    @PostMapping
    @RequestMapping("/adduser")
    public ResponseEntity<BasicResponse> addUser(@RequestBody UserRequest request) {
        BasicResponse response = personService.addUser(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    @PutMapping
    @RequestMapping("/updateuser")
    public ResponseEntity<BasicResponse> updateUser(@RequestBody UserRequest request,
                                                    @RequestParam String dni) {

        BasicResponse response = personService.updateUser(request, dni);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    @DeleteMapping
    @RequestMapping("/deleteuser")
    public ResponseEntity<BasicResponse> deleteUser(@RequestParam String dni) {
        BasicResponse response = personService.deleteUser(dni);
        return ResponseEntity.status(response.getCode()).body(response);
    }




}
