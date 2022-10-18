package somaMVP.domain.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class FileInferenceService {
    @Value("${AWS_ML_URL}")
    public String ML_URL;
    public Mono<String> mlUpload(MultipartFile files) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder(); // 여기 없으면 이슈가 발생함
        builder.part("source", files.getResource());
        return WebClient.create(ML_URL)
                .post()
                .uri("/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public String test() {
        return WebClient.create(ML_URL)
                .get()
                .uri("/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}