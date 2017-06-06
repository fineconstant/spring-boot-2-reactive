package isp.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactiveClientApplication {
    @Bean
    WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner cmd(WebClient webClient) {
        return args -> webClient.get()
                                .uri("events")
                                .accept(MediaType.TEXT_EVENT_STREAM)
                                .exchange()
                                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Event.class))
                                .doOnNext(System.out::println)
                                .blockLast();
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveClientApplication.class, args);
    }
}
