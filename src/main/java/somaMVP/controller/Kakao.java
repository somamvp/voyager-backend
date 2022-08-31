package somaMVP.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
public class Kakao {
    @Value("${KAKAO_API_KEY}")
    public String restapi;
    private String javascript;
    private String clientSecret;
}
