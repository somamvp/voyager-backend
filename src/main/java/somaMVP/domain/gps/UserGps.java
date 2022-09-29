package somaMVP.domain.gps;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "user")
public class UserGps {

    @Id
    private String id;
    private final Double gpsX;
    private final Double gpsY;
    private LocalDateTime createdAt;

    public UserGps(String id, Double gpsX, Double gpsY) {
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.createdAt = LocalDateTime.now();
    }
    public UserGps(){
        this.gpsX = 0.0;
        this.gpsY = 0.0;
    }
}