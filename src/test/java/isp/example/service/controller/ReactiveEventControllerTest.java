package isp.example.service.controller;

import isp.example.service.ReactiveServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReactiveEventControllerTest {

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        this.webTestClient = WebTestClient.bindToApplicationContext(SpringApplication.run(ReactiveServiceApplication.class))
                                          .build();
//        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080")
//                                          .build();
    }

    @Test
    public void eventById() throws Exception {
        this.webTestClient.get()
                          .uri("events/10")
                          .accept(MediaType.APPLICATION_JSON_UTF8)
                          .exchange()
                          .expectStatus().isOk();
    }

}