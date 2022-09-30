package pe.ineapp.ineapppersonaservice.Person.application.service;

import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.UserResponse;

import java.util.List;

public interface PersonService {


    public BasicResponse addUser(UserRequest request);

    public UserResponse getAll();

}
