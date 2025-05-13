package com.bootcamp.springBoot.Person.service;
import com.bootcamp.springBoot.Person.entity.Person;
import com.bootcamp.springBoot.Person.model.PersonRequest;
import com.bootcamp.springBoot.Person.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//3

@Service
public class PersonService {
    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Transactional
    // nanti juga saya mau coba nanya perihal ini mas, kenapa harus buat PersonRequest, dan kenapa ngga langsung ke entity nya saja ya?
    public Person createPerson(PersonRequest request) {
        Person person = Person.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .gender(request.getGender())
                .build();
        return personRepository.save(person);
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    public List<Person> findByFirstname(String firstname) {
        return personRepository.findByFirstname(firstname);
    }
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }
    @Transactional
    public Person updatePerson(Long id, PersonRequest request) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    Person updatedPerson = Person.builder()
                            .id(existingPerson.getId())
                            .firstname(request.getFirstname())
                            .lastname(request.getLastname())
                            .gender(request.getGender())
                            .build();
                    return personRepository.save(updatedPerson);
                })
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    }
    public void deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new RuntimeException("Person not found with id: " + id);
        }
    }
}