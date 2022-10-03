package somaMVP.domain.gis;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GisDto{

    private Long id;
    private String zebraId; // 횡단보도 아이디
    private String gpsY;
    private String gpsX;
    private String address;
    private String lightExist; // 신호등 여부
    private String acousticExist; // 음향 신호기 여부
    private LocalDateTime createdDate;
    public static GisDto of(Gis gis) {
        return GisDto.builder()
                .id(gis.getId())
                .zebraId(gis.getZebraId())
                .gpsY(gis.getGpsY())
                .gpsX(gis.getGpsX())
                .address(gis.getAddress())
                .lightExist(gis.getLightExist())
                .acousticExist(gis.getAcousticExist())
                .createdDate(gis.getCreatedDate())
                .build();
    }
}
