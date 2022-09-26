package somaMVP.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor()
@Builder
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Gis extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키
    private String crossWalkId; // 횡단보도 아이디
    private String gpsX;
    private String gpsY;
    private String lightExist; // 신호등 여부
    private String acousticExist; // 음향 신호기 여부
}
