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
    private Double gpsY;
    @NotNull
    private Double gpsX;
    private Double distance;

    public UserGps toEntity() {
        return new UserGps(gpsY, gpsX);
    }

    public Map<String, Object> toMap(String id, String gpsY, String gpsX) {
        Map<String, Object> toMap = new HashMap<>();
        toMap.put("userId", id);
        toMap.put("gpsY", gpsY);
        toMap.put("gpsX", gpsX);
        return toMap;
    }
}