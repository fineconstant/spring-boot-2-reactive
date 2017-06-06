package isp.example.service;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveServiceApplication {

    @Bean
    CommandLineRunner cmd(ReactiveUserRepository userRepository) {
        return args ->
                userRepository.saveAll(Flux.just(
                        new User("John"),
                        new User("Adam"),
                        new User("Adam"),
                        new User("Robert")))
                              .log()
                              .doOnComplete(() -> System.out.println("Inserted sample users"))
                              .subscribe();
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class, args);
    }
}


