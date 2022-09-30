package pe.ineapp.ineapppersonaservice.Person.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.application.service.PersonService;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.UserResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;



    @GetMapping
    @RequestMapping("/getAll")
    public UserResponse getAll() {
        return personService.getAll();
    }

    @GetMapping
    @RequestMapping("/getbyid")
    public Person getById(){
        return null;
    }


    @PostMapping
    @RequestMapping("/adduser")
    public ResponseEntity<BasicResponse> adduser(@RequestBody UserRequest request){
        BasicResponse response = personService.addUser(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }




}
