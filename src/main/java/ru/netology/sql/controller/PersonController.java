package ru.netology.sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.sql.personEnity.PersonEntity;
import ru.netology.sql.repository.PersonRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @ResponseBody
    @GetMapping("/people/cities/{city}")
    public List<PersonEntity> getPeopleByCity(@PathVariable String city) {
        return personRepository.findByCityOfLiving(city);
    }

    @ResponseBody
    @GetMapping("/people/names/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username, @AuthenticationPrincipal UserDetails currentUser) {
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_admin") || grantedAuthority.getAuthority().equals("ROLE_moderator"));
        if (!isAdmin && !currentUser.getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ошибка доступа");
        }

        return ResponseEntity.ok(personRepository.findByName(username));
    }

    @ResponseBody
    @GetMapping("/people/ages/{age}")
    public List<PersonEntity> getPeopleByAge(@PathVariable int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    @ResponseBody
    @GetMapping("/people/full-names/{name}/{surname}")
    public Optional<PersonEntity> getPersonByFullName(@PathVariable String name, @PathVariable String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    @ResponseBody
    @GetMapping("/people")
    public List<PersonEntity> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/welcome")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String getIndexPage(Principal principal) {
        if (principal != null && principal.getName() != null) {
            UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_admin"))) {
                return "admin";
            }
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_moderator"))) {
                return "moder";
            }
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_user"))) {
                return "user";
            }
        }
        return "index";
    }
}

