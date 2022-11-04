package somaMVP.domain.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileInferenceService {
    @Value("${AWS_ML_URL}")
    public String ML_URL;

    public final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs()
                    .maxInMemorySize(10 * 1024 * 1024)
            ).build();

    public String test() {
        return WebClient.create(ML_URL)
                .get()
                .uri("/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Mono<Object> mlUpload(String gpsId, MultipartFile file, Boolean isRotate, Integer sequenceNumber, String gpsInfo, Boolean crossStart, Boolean shouldLightExist) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder(); // 여기 없으면 이슈가 발생
        builder.part("session_id", gpsId);
        builder.part("source", file.getResource());

        if (gpsInfo != null) {
            builder.part("gps", gpsInfo);
        }
        if (isRotate != null) {
            builder.part("is_rot", isRotate);
        }
        if (sequenceNumber != null) {
            builder.part("sequence_number", sequenceNumber);
        }
        if (crossStart != null) {
            builder.part("cross_start", crossStart);
        }
        if (shouldLightExist != null) {
            builder.part("should_light_exist", shouldLightExist);
        }

        return WebClient
                .builder()
                .exchangeStrategies(exchangeStrategies)
                .build()
                .post()
                .uri(ML_URL + "/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Object.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Object create() {
        return WebClient.builder()
                .build()
                .get()
                .uri(ML_URL + "/create")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        // redis에 state 저장 하는 로직 필요 똑같은데 cs만 올거임.
    }
    @Bean
    public WebClient getWebClientBuilder(){
        return   WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .build();
    }
}