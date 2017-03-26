package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    private final UserRepository userRepository;

    public ReactiveController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    public Flux<User> listUsers() {
        // can return both Reactor's Flux and RxJava's Flowable
        // Publisher 0, 1, N
        return Flux.fromIterable(userRepository.findAll());
    }

    @GetMapping("users/{id}")
    public Mono<User> listUsersById(@PathVariable Long id) {
        // can return both Reactor's Flux and RxJava's Flowable
        // Mono 0, 1
        return Mono.justOrEmpty(userRepository.findOne(id));
    }
}
