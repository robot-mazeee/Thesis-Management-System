package pt.ulisboa.tecnico.rnl.dei.tms.person.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.tms.person.domain.Person;

import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByType(Person.PersonType type);
    

    // SELECT * FROM person WHERE name = ?;
    // public Optional<Person> findById(long id);

    /*
    JPQL (Java Persistence Query Language):

    @Query("SELECT p FROM Person p WHERE p.age > :age")
    List<Person> findPeopleOlderThan(@Param("age") int age);

    generates: SELECT * FROM person WHERE age > ?;
    */
}
