package somaMVP.domain.gps;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class UserGpsDto {
    @NotNull
    private Double gpsX;
    @NotNull
    private Double gpsY;

    public UserGps toEntity() {
        return new UserGps(gpsX, gpsY);
    }

    public Map<String, Object> toMap(String id, String gpsX, String gpsY) {
        Map<String, Object> toMap = new HashMap<>();
        toMap.put("userId", id);
        toMap.put("gpsX", gpsX);
        toMap.put("gpsY", gpsY);
        return toMap;
    }
}