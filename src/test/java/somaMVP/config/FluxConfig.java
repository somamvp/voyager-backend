package somaMVP.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FluxConfig {
    @Value("${AWS_ML_URL}")
    public String ML_URL;

    @Bean
    public WebClient webClient() {
        return WebClient.create(ML_URL);
    }
    @Bean
    public MultipartBodyBuilder MultipartBodyBuilder() {
        return new MultipartBodyBuilder();
    }
}