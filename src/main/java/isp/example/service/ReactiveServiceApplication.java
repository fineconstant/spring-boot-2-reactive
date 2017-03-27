package isp.example.service;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import isp.example.service.util.MongoUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveServiceApplication {
    private final MongoUtils utils;

    public ReactiveServiceApplication(MongoUtils utils) {
        this.utils = utils;
    }

    @Bean
    CommandLineRunner cmd(ReactiveUserRepository userRepository) {
        return args ->
                userRepository.save(Flux.just(new User("John"), new User("Adam"), new User("Adam"), new User("Robert")))
                              .doOnComplete(() -> System.out.println("Inserted sample users"))
                              .subscribe(System.out::println);

    }

//    TODO: functional router example
//    @Bean
//    RouterFunction<?> router(ReactiveUserRepository userRepository, UserHandler handler) {
//        return route(GET("reactive/users"), handler::users)
//                .and(route(GET("reactive/users/{id}"), handler::userById));
//
////        return route(GET("reactive/users"),
////                     request -> {
////                         Flux<User> allUsers = userRepository.findAll();
////                         return ServerResponse.ok().body(fromPublisher(allUsers, User.class));
////                     }).and(
////                route(GET("reactive/users/{id}"),
////                      request -> {
////                          Mono user = Mono.justOrEmpty(request.pathVariable("id"))
////                                          .map(Long::valueOf)
////                                          .map(userRepository::findOne);
////                          return ServerResponse.ok().body(fromObject(user));
////                      }));
//    }

    //    TODO: compare repository return List<User> vs Flux<User> and build json
    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class, args);
    }
}


