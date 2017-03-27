package isp.example.service.util;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
public class MongoUtils {
    private final ReactiveUserRepository userRepository;

    public MongoUtils(ReactiveUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    TODO: implement
    public Flux insertSampleData(int size) {
        return Flux.range(0,size);
    }
}
