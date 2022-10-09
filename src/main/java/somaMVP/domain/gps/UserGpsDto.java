package somaMVP.domain.gps;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Getter
@Slf4j
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
public class UserGpsDto {
    @NotNull
    private Double gpsY;
    @NotNull
    private Double gpsX;
    private Double distance;

    public UserGps toEntity() {
        return new UserGps(gpsY, gpsX);
    }
}