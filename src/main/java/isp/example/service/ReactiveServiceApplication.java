package isp.example.service;

import isp.example.service.handler.UserHandler;
import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReactiveServiceApplication {

    @Bean
    CommandLineRunner cmd(ReactiveUserRepository userRepository) {
        return args -> {
            System.out.println("=== Inserting sample data");
            userRepository.save(Flux.just(new User("John"),
                                          new User("Adam"),
                                          new User("Adam"),
                                          new User("Robert")))
                          .subscribe(System.out::println);

            System.out.println("=== Find all results:");
            userRepository.findAll()
                          .subscribe(System.out::println);
        };
    }

    @Bean
    RouterFunction<?> router(ReactiveUserRepository userRepository, UserHandler handler) {
        return route(GET("reactive/users"), handler::users)
                .and(route(GET("reactive/users/{id}"), handler::userById));

//        return route(GET("reactive/users"),
//                     request -> {
//                         Flux<User> allUsers = userRepository.findAll();
//                         return ServerResponse.ok().body(fromPublisher(allUsers, User.class));
//                     }).and(
//                route(GET("reactive/users/{id}"),
//                      request -> {
//                          Mono user = Mono.justOrEmpty(request.pathVariable("id"))
//                                          .map(Long::valueOf)
//                                          .map(userRepository::findOne);
//                          return ServerResponse.ok().body(fromObject(user));
//                      }));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class, args);
    }
}


