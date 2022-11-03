package somaMVP.domain.file;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Data
@Getter
@RedisHash(value = "state", timeToLive = 300)
public class ObjectDto {
    public String id;
    public String yoloResult;

    public ObjectDto(String id, String yoloResult) {
        this.id = id;
        this.yoloResult = yoloResult;
    }
}