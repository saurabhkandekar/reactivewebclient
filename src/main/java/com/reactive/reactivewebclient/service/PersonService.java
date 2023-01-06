package com.reactive.reactivewebclient.service;

import com.reactive.reactivewebclient.model.PersonRequest;

import com.reactive.reactivewebclient.model.PersonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class PersonService {

    private final WebClient.Builder webclient;

    @Value("${base.url}")
    private String baseurl;

    public PersonService(WebClient.Builder webclient) {
        this.webclient = webclient;
    }

    public Mono<PersonRequest> getById(Long id) {
        log.info("get Person By id:" + id);
        return webclient.build().get()
                .uri(baseurl + "/person/{id}", id)
                .retrieve()
                .bodyToMono(PersonRequest.class);

    }

    public Mono<PersonRequest> addPerson(PersonRequest personRequest) {
        log.info("add Persons");
        return webclient.build().post()
                .uri(baseurl+"/persons")
                .body(Mono.just(personRequest),PersonRequest.class)
                .retrieve()
                .bodyToMono(PersonRequest.class);


    }

    public Flux<PersonRequest> getAll() {
        log.info("get All Person");
        return webclient.build().get()
                .uri(baseurl + "/persons")
                .retrieve()
                .bodyToFlux(PersonRequest.class);
    }

    public Mono<Void> deleteById(Long id) {
        log.info("delete Person By Id:" + id);
        return webclient.build().delete()
                .uri(baseurl + "/person/{id}", id)
                .retrieve()
                .bodyToMono(void.class);

    }

    public Mono<PersonRequest> updatePerson(Long id, PersonRequest personRequest) {
        log.info("update Person By Id:" + id);
        return webclient.build().put()
                .uri(baseurl + "/person/{id}")
                .retrieve()
                .bodyToMono(PersonRequest.class);

    }
}

