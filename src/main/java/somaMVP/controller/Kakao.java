package somaMVP.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter @Setter
public class Kakao {
    @Value("${KAKAO_API_KEY}")
    private String restapi;
    private String javascript;
    private String clientSecret;
}
