package somaMVP.domain.gps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateGpsDto {
    private String gpsY;
    private String gpsX;
    public Map<String, Object> toMap(String id) {
        Map<String, Object> toMap = new HashMap<>();
        toMap.put("userId", id);
        toMap.put("gpsY", this.gpsY);
        toMap.put("gpsX", this.gpsX);
        return toMap;
    }
}
