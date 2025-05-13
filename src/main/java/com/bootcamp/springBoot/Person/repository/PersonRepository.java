package com.bootcamp.springBoot.Person.repository;

import com.bootcamp.springBoot.Person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// 2
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstname(String firstname);
}