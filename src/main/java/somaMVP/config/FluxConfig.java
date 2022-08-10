package somaMVP.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class FluxConfig {
    @Value("${AWS_ML_URL}")
    public String ML_URL;

    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.create(ML_URL);
        log.info("webClient : {}", ML_URL);
        return webClient;
    }
    @Bean
    public MultipartBodyBuilder multipartBodyBuilder() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        log.info("body builder created");
        return builder;
    }
}