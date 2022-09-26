package somaMVP.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class KakaoController {
    public final Kakao kakao = new Kakao();

    @GetMapping("/kakao")
    public String search(@RequestParam String query){
        Mono<String> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json").queryParam("query", query).build())
                .header("Authorization", "KakaoAK " + kakao.getRestapi())
                .retrieve().bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
        return mono.block();
    }
    @GetMapping("/kakao2")
    public Mono<String> search2(@RequestParam String query){
        return WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json").queryParam("query", query).build())
                .header("Authorization", "KakaoAK " + kakao.getRestapi())
                .retrieve().bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }
    @GetMapping("/just")
    public Mono<String> just(){
        return Mono.just("just");
    }
}
