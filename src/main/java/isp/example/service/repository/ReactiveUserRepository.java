package isp.example.service.repository;

import isp.example.service.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

// TODO: fix org.springframework.data.mapping.PropertyReferenceException: No property delete found for type User!
public interface ReactiveUserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByName(String name);
}
