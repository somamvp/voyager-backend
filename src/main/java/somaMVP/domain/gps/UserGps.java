package somaMVP.domain.gps;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "user")
public class UserGps {

    private String id;
    private Double gpsY;
    private Double gpsX;
    private final LocalDateTime createdAt;

    public UserGps(Double gpsY, Double gpsX) {
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.createdAt = LocalDateTime.now();
    }
    public UserGps(String id){
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }
}