package somaMVP.domain.gps;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "user")
public class UserGps {

    private String id;
    private Double gpsX;
    private Double gpsY;
    private final LocalDateTime createdAt;

    public UserGps(Double gpsX, Double gpsY) {
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.createdAt = LocalDateTime.now();
    }
    public UserGps(String id){
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }
}