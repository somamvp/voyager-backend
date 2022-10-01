package somaMVP.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import somaMVP.domain.file.FileInferenceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@DisplayName("FileUploadControllerTest")
class FileInferenceServiceTest {
    //TODO: 테스트 오류 수정 :80 쪽으로 요청이 가는데, 8080으로 요청이 가야함
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) // Deep Stub
    WebClient webClient;
    @InjectMocks
    FileInferenceService service;
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