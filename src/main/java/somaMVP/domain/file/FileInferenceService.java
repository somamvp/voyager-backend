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
    public Mono<Object> mlUpload(MultipartFile files, Boolean isRotate, Double gpsX, Double gpsY, Double gpsHeading, Double gpsSpeed) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder(); // 여기 없으면 이슈가 발생함
        builder.part("source", files.getResource());
        builder.part("is_rot", isRotate);
        if (gpsX != null) {
            builder.part("gps_x", gpsX);
        }
        if (gpsY != null) {
            builder.part("gps_y", gpsY);
        }
        if (gpsHeading != null) {
            builder.part("gps_heading", gpsHeading);
        }
        if (gpsSpeed != null) {
            builder.part("gps_speed", gpsSpeed);
        }
        if (gpsX != null && gpsY != null) {
            builder.part("gps", true);
        }
        return WebClient.create(ML_URL)
                .post()
                .uri("/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Object.class)
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

    public Mono<Object> mlUpload(MultipartFile file, Boolean isRotate, String gpsInfo, String settings) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder(); // 여기 없으면 이슈가 발생함
        builder.part("source", file.getResource());
        builder.part("is_rot", isRotate);
        builder.part("settings", settings);
        if (gpsInfo != null) {
            builder.part("gps", gpsInfo);
        }
        return WebClient.create(ML_URL)
                .post()
                .uri("/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Object.class)
                .timeout(Duration.ofMillis(5000));
    }
}