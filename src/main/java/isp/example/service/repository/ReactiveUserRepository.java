package isp.example.service.repository;

import isp.example.service.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReactiveUserRepository extends ReactiveCrudRepository<User, String> {
    Flux<User> findByName(String name);
}
