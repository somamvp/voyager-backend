package somaMVP.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class FluxInferenceService {
    public final WebClient webClient;
    public final MultipartBodyBuilder builder;
    public Mono<String> mlUpload(MultipartFile file){
        builder.part("img", file.getResource());
        return webClient
                .post()
                .uri("/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }
    public String test() {
        return webClient.get()
                .uri("/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}