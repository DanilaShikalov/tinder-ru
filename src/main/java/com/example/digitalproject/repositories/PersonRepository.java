package com.example.digitalproject.repositories;

import com.example.digitalproject.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select distinct p from Person p inner join User u on p.user = u where :token\n" +
            "        in (select t.token from Token t where t.user = u and t.expired = false and t.revoked = false)")
    List<Person> getPersonByToken(@Param("token") String token);
}
