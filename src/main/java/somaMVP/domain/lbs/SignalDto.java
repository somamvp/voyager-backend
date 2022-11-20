package somaMVP.domain.lbs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalDto {
    @NotNull
    private String id; // 횡단보도 아이디
    private Integer pedSignal; // 보행자 신호등 여부
    private Double length;
}
