package isp.example.service.handler;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class UserHandler {
    private final ReactiveUserRepository userRepository;

    UserHandler(ReactiveUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> users(ServerRequest request) {
        Flux<User> allUsers = userRepository.findAll();
        return ServerResponse.ok().body(fromPublisher(allUsers, User.class));
    }

    public Mono<ServerResponse> userById(ServerRequest request) {
        Mono user = Mono.justOrEmpty(request.pathVariable("id"))
                        .map(userRepository::findOne);

//            TODO: check if valid
//            return ServerResponse.ok().body(fromPublisher(user, User.class));
        return ServerResponse.ok().body(fromObject(user));
    }
}
