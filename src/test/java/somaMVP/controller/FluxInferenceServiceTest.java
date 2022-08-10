package somaMVP.controller;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import somaMVP.response.ImageResponse;
import somaMVP.service.FluxInferenceService;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@DisplayName("FluxController Test")
class FluxInferenceServiceTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) // Deep Stub
    WebClient webClient;
    @InjectMocks
    FluxInferenceService service;
    @Value("${uploadDir}")
    private String uploadDir;

    @DisplayName("ok Test")
    @Test
    void test() {
        // given
        given(webClient.get()
                .uri("/test")
                .retrieve()
                .bodyToMono(String.class))
                .willReturn(Mono.just("ok"));
        // when
        String result = service.test();
        // then
        assertThat(result).isEqualTo("ok");
    }
}