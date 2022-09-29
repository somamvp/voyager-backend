package somaMVP.domain.gps;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserGpsDto {
    @NotNull
    private Double gpsX;
    @NotNull
    private Double gpsY;


    public UserGps toEntity(String id) {
        return new UserGps(id, gpsX, gpsY);
    }
}