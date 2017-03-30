package isp.example.service;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Operators {

    @Test
    public void words() {
        Flux.just("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
            .flatMap(s -> Flux.fromArray(s.split(" ")))
//            .log()
            .sort(String::compareToIgnoreCase)
            .zipWith(Flux.range(1, Integer.MAX_VALUE))
            .map(tuple -> tuple.getT2() + ": " + tuple.getT1())
            .subscribe(System.out::println);
    }

    @Test
    public void delayedHelloWorld() {
        Mono.just("Hello")
            .concatWith(Mono.just("world").delaySubscription(Duration.ofSeconds(1)))
//            .toStream()
//            .forEach(System.out::println);
            .subscribe(System.out::println);
    }

    @Test
    public void firstEmitting() {
        Mono<String> a = Mono.just("too late")
                             .delaySubscriptionMillis(500);
        Flux<String> b = Flux.just("faster", "stream", "elements")
                             .delaySubscriptionMillis(250);

        Flux.firstEmitting(a, b)
            .toIterable()
            .forEach(System.out::println);
    }
}
