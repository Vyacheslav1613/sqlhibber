package ru.netology.sql.controller;

import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.sql.personEnity.PersonEntity;
import ru.netology.sql.repository.PersonRepository;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people/cities/{city}")
    public List<PersonEntity> getPeople(@PathVariable String city) {
        return personRepository.findAllByCityOfLiving(city);
    }
    @GetMapping("/people/names/{name}")
    public List<PersonEntity> getPeopleByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }
    @GetMapping("/people")
    public List<PersonEntity> getPeopleAll() {
        return personRepository.findAll();
    }
}