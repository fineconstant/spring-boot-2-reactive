package isp.example.service.repository;

import isp.example.service.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findByName(String name);
}
