package com.example.controller;

import com.example.repository.UserRepository;
import com.example.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveUserController {

    private final UserRepository userRepository;

    public ReactiveUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    public Flux<User> users() {
        // can return both Reactor's Flux and RxJava's Flowable
        // Publisher 0, 1, N
        return userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public Mono<User> userById(@PathVariable Mono<Long> id) {
        // can return both Reactor's Flux and RxJava's Flowable
        // Mono 0, 1
        return userRepository.findOne(id);
    }

    @GetMapping("by_name/{name}")
    public Flux<User> userByName(@PathVariable Mono<String> name) {
        return userRepository.findByName(name);
    }
}
