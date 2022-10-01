package somaMVP.domain.gis;

import lombok.*;
import somaMVP.domain.BaseTimeEntity;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GisDto extends BaseTimeEntity {
    private Long id; // 기본 키
    private String crossWalkId; // 횡단보도 아이디
    private String gpsX;
    private String gpsY;
    private String lightExist; // 신호등 여부
    private String acousticExist; // 음향 신호기 여부

    public Gis toEntity() {
        return new Gis(id, crossWalkId, gpsX, gpsY, lightExist, acousticExist);
    }
}
