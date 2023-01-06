package com.reactive.reactivewebclient.controller;

import com.reactive.reactivewebclient.model.PersonRequest;
import com.reactive.reactivewebclient.service.PersonService;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons")
    public Mono<PersonRequest> addPerson(@RequestBody PersonRequest personRequest) {
        return personService.addPerson(personRequest);
    }

    @GetMapping("/person/{id}")
    public Mono<PersonRequest> getById(@PathVariable Long id) {
        Mono<PersonRequest> personRequest = personService.getById(id);
        return personRequest;
    }

    @GetMapping("/persons")
    public Flux<PersonRequest> getAll() {
        Flux<PersonRequest> personRequest = personService.getAll();
        return personRequest;
    }

    @PutMapping("/person/{id}")
    public Mono<PersonRequest> updatePerson(@PathVariable Long id, @RequestBody PersonRequest personRequest) {
        Mono<PersonRequest> personRequestMono= personService.updatePerson(id, personRequest);
        return personRequestMono;
    }

    @DeleteMapping("/person/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return personService.deleteById(id);
    }
}
