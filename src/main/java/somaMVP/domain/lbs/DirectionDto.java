package somaMVP.domain.lbs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDto {
    private Double startY;
    private Double startX;
    private Double endY;
    private Double endX;
}