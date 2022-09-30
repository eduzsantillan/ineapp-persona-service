package pe.ineapp.ineapppersonaservice.Person.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {


}
