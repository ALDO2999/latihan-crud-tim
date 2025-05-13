package com.bootcamp.springBoot.Person.controller;


import com.bootcamp.springBoot.Person.entity.Person;
import com.bootcamp.springBoot.Person.model.PersonRequest;
import com.bootcamp.springBoot.Person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//5

@RequestMapping(value = "/api/bootcamp/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequest request){
        return ResponseEntity.ok(personService.createPerson(request));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonById(id);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonRequest request) {
        try {
            Person updatedPerson = personService.updatePerson(id, request);
            return ResponseEntity.ok(updatedPerson);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok("Data berhasil dihapus.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @GetMapping("/search/firstname")
    public ResponseEntity<List<Person>> getPersonsByFirstname(@RequestParam String firstname) {
        List<Person> persons = personService.findByFirstname(firstname);
        return ResponseEntity.ok(persons);
    }

}
