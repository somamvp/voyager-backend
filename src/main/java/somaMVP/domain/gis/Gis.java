package somaMVP.domain.gis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Gis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키
    private String zebraId; // 횡단보도 아이디
    private Point geometry;
    private String gpsY;
    private String gpsX;
    private String address;
    private String lightExist; // 신호등 여부
    private String acousticExist; // 음향 신호기 여부
    @CreatedDate
    private LocalDateTime createdDate;
}
