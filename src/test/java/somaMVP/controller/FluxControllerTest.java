package somaMVP.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import somaMVP.dto.Customer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FluxControllerTest {
    @Test
    void findName() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> hello = webClient.get()
                .uri("/api/posts/hello?name={name}", "wonwoo")
                .retrieve()
                .bodyToMono(String.class);

        StepVerifier.create(hello)
                .expectNext("hello wonwoo")
                .verifyComplete();
    }
}