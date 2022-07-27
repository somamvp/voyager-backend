package somaMVP.controller;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import somaMVP.dto.Customer;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FluxController {
    @Value("${AWS_ML_URL}")
    public String ML_URL;
    @Autowired
    WebClient webClient;
    public Flux<Customer> findAll(){
        return webClient.get()
                .uri("/Customer")
                .retrieve()
                .bodyToFlux(Customer.class);
    }
    public Mono<Customer> findName(Integer id){
        return webClient.get()
                .uri("/Customer" + id)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    WebClient client = WebClient.builder()
            .baseUrl(ML_URL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

    WebClient client2 = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
}
