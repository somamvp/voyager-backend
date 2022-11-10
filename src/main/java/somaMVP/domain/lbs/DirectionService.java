package somaMVP.domain.lbs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class DirectionService {
    @Value("${LBS_URL}")
    public String LBS_URL;

    public Object getDirection(DirectionDto directionDto) {
        return WebClient.builder()
                .build()
                .post()
                .uri(LBS_URL + "/getDirections")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(directionDto))
                .retrieve()
                .bodyToMono(Object.class)
                .block(Duration.ofSeconds(5));
    }
}
