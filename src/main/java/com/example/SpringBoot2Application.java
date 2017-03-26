package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBoot2Application {

    private final UserRepository userRepository;

    public SpringBoot2Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    CommandLineRunner cmd() {
        return args -> {
            userRepository.save(Arrays.asList(new User("John"),
                                              new User("Adam"),
                                              new User("Adam"),
                                              new User("Robert")))
                          .subscribe(System.out::println);
            userRepository.findAll()
                          .subscribe(System.out::println);

        };

//        return args -> Mono.delayMillis(3000)
//                           .map(d -> "Reactor")
//                           .then(t -> Mono.just(t + " word"))
//                           .elapsed()
//                           .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Application.class, args);
    }
}
