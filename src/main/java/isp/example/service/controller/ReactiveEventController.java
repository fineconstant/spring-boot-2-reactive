package isp.example.service.controller;

import isp.example.service.model.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
public class ReactiveEventController {

    @GetMapping("events/{id}")
    public Mono<Event> eventById(@PathVariable long id) {
        return Mono.just(new Event(id, LocalDateTime.now()));
    }

    @GetMapping(value = "events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events() {
        Flux<Event> eventFlux = Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), LocalDateTime.now())));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));

        return eventFlux.zipWith(durationFlux)
                        .map(Tuple2::getT1);
    }
}
