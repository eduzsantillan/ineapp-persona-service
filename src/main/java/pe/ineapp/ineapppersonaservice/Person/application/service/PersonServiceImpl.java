package pe.ineapp.ineapppersonaservice.Person.application.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.repository.PersonRepository;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infrastructure.response.UserResponse;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
public class PersonServiceImpl implements PersonService{


    private static final String PASSWORD = "secreto";

    @Autowired
    private PersonRepository personRepository;

    @Override
    public BasicResponse addUser(UserRequest request) {

        try{
            if(request.getPassword().equals(PASSWORD)){
                personRepository.save(this.buildPersonFromRequest(request));
                return BasicResponse.whenSuccess();
            }else{
                return BasicResponse.whenPassNotMatch();
            }
        }catch(Exception e){
            log.error(e.getMessage());
                return BasicResponse.whenError(e.getMessage());
        }
    }

    @Override
    public UserResponse getAll(){
        List<Person> personList= personRepository.findAll();

        if(personList.isEmpty()) {
            return UserResponse.builder()
                    .personList(null)
                    .basicResponse(BasicResponse.whenNoDataFound("User"))
                    .build();
        }

        return UserResponse.builder()
                .personList(personList)
                .basicResponse(BasicResponse.whenSuccess())
                .build();
    }

    @Override
    public UserResponse getByDni(String dni){

        try{
            Person person = personRepository.findByDni(dni);

            if(person != null){
                return UserResponse.builder()
                        .personList(List.of(person))
                        .basicResponse(BasicResponse.whenSuccess())
                        .build();
            }else{
                return UserResponse.builder()
                        .personList(null)
                        .basicResponse(BasicResponse.whenNoDataFound("User"))
                        .build();
            }
        }catch(Exception e){
            return UserResponse.builder()
                    .personList(null)
                    .basicResponse(BasicResponse.whenError(e.getMessage()))
                    .build();
        }

    }

    @Transactional
    @Override
    public BasicResponse updateUser(UserRequest request ,String dni){
        try{
            //Validar que exista el usuario que se desea modificar
            Person person = personRepository.findByDni(dni);

            if(person == null){
                return BasicResponse.whenNoDataFound("User con dni "+dni);
            }else{
                //Validar que la clave sea correcta
                if(request.getPassword().equals(PASSWORD)){

                    person.setName(request.getName()!=null && !request.getName().isBlank() ? request.getName() : person.getName());
                    person.setLastName(request.getLastName()!=null && !request.getLastName().isBlank() ? request.getLastName() : person.getLastName());
                    person.setEmail(request.getEmail()!=null && !request.getEmail().isBlank() ? request.getEmail() : person.getEmail());
                    person.setBirthDate(request.getBirthDate()!=null ? request.getBirthDate() : person.getBirthDate());
                    person.setDni(request.getDni()!=null && !request.getDni().isBlank() ? request.getDni() : person.getDni());

                    return BasicResponse.whenSuccess();
                }else{
                    return BasicResponse.whenPassNotMatch();
                }
            }
        }catch (Exception e){
            return BasicResponse.whenError(e.getMessage());
        }
    }

    public BasicResponse deleteUser(String dni){
        try{
            Person person = personRepository.findByDni(dni);

            if(person == null){
                return BasicResponse.whenNoDataFound("User con dni "+dni);
            }else{
                personRepository.delete(person);
                return BasicResponse.whenSuccess();
            }
        }catch (Exception e){
            return BasicResponse.whenError(e.getMessage());
        }
    }

    public Person buildPersonFromRequest(UserRequest request){

        return Person.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .email(request.getEmail())
                .birthDate(request.getBirthDate()).build();
    }

}
