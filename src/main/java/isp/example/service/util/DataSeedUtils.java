package isp.example.service.util;

import isp.example.service.model.User;
import isp.example.service.repository.ReactiveUserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.LongStream;

@Service
public class DataSeedUtils {
    private final ReactiveUserRepository userRepository;

    public DataSeedUtils(ReactiveUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void insertSampleData(int number) {
        ParallelFlux<User> users = Flux.fromStream(LongStream.range(0L, number).boxed())
                                       .subscribeOn(Schedulers.elastic())
                                       .publishOn(Schedulers.elastic())
                                       .parallel()
                                       .map(n -> new User("User #" + n));
        userRepository.saveAll(users)
                      .subscribe();
    }
}
