package somaMVP.domain.gis;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GisDtos {
    private Integer dataCount;
    private List<GisDto> data;
}