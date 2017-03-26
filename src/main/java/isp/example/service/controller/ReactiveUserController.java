package isp.example.service.controller;

import isp.example.service.repository.ReactiveUserRepository;
import isp.example.service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveUserController {

    private final ReactiveUserRepository userRepository;

    public ReactiveUserController(ReactiveUserRepository userRepository) {
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
    public Flux<User> userByName(String name) {
        return userRepository.findByName(name);
    }
}
