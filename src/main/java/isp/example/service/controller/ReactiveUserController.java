package isp.example.service.controller;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
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
        // Flux 0, 1, N
        return userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public Mono<User> userById(@PathVariable String id) {
        // Mono 0, 1
        return userRepository.findById(id);
    }

    @GetMapping("users/count")
    public Mono<Long> usersCount() {
        return userRepository.count();
    }

    @GetMapping("users/by_name/{name}")
    public Flux<User> userByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }
}
