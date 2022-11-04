package pe.ineapp.ineapppersonaservice.Person.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

    public Person findByDni(String dni);


}
