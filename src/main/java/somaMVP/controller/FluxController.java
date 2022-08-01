package somaMVP.controller;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import somaMVP.annotation.RunningTime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

@Component
@Slf4j
public class FluxController {
    @Value("${AWS_ML_URL}")
    public String ML_URL;
    WebClient webClient = WebClient.builder()
            .baseUrl(ML_URL)
            .build();
//    @RunningTime
//    public void mlUpload(MultipartFile file) {
//        MultipartBodyBuilder builder = new MultipartBodyBuilder();
//        builder.part("img", file.getResource());
//        log.info(file.getResource().toString());
//        //log.info("mlupload");
//        WebClient.create()
//                .method(HttpMethod.POST)
//                .uri(ML_URL +"/upload")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(builder.build()))
//                .exchangeToMono(response -> {
//                    if (response.statusCode().equals(HttpStatus.OK)) {
//                        log.info("ML 성공");
//                        return response.bodyToMono(HttpStatus.class).thenReturn(response.statusCode());
//                    } else {
//                        throw new RuntimeException("업로드 실패");
//                    }
//                });
//    }
    public Mono<String> post(){
        return WebClient.builder().baseUrl(ML_URL + "/hello")
                .build().post()
                .retrieve().bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }
    @RunningTime
    public Mono<String> mlUpload(MultipartFile file){
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("img", file.getResource());
        return WebClient.builder().baseUrl(ML_URL + "/upload")
                .build().post()
                .uri("")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }
}