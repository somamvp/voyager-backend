package somaMVP.domain.lbs;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity(name = "cross_signal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Signal {
    @Id
    @Column(nullable = false)
    private String length;
    private String id; // 횡단보도 아이디
    private Integer pedSig; // 보행자 신호등 여부
}
