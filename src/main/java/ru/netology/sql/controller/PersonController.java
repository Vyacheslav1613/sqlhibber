package ru.netology.sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.sql.personEnity.PersonEntity;
import ru.netology.sql.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people/cities/{city}")
    public List<PersonEntity> getPeopleByCity(@PathVariable String city) {
        return personRepository.findByCityOfLiving(city);
    }

    @GetMapping("/people/names/{name}")
    public List<PersonEntity> getPeopleByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }

    @GetMapping("/people/ages/{age}")
    public List<PersonEntity> getPeopleByAge(@PathVariable int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    @GetMapping("/people/full-names/{name}/{surname}")
    public Optional<PersonEntity> getPersonByFullName(@PathVariable String name, @PathVariable String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    @GetMapping("/people")
    public List<PersonEntity> getAllPeople() {
        return personRepository.findAll();
    }
}
