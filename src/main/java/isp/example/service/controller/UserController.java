package isp.example.service.controller;

import isp.example.service.model.User;
import isp.example.service.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("synchronous/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("synchronous/users/iterable")
    public Iterable<User> usersIterable() {
        return userRepository.findAll();
    }
}
