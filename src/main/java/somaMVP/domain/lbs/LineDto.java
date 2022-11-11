package somaMVP.domain.lbs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineDto {
    private String id;
    private Properties properties;
    private Geometry geometry;
    private String type;

    @Data
    public static class Properties {
        private int index;
        private int difficulty;
        private int time;
        private String crossingType;
        private String pathType;
        @JsonProperty("srid")
        private int srId;
        private int incline;
        private double length;
        private String nodebId;
        private String nodeaId;
        private String pedSignal;
    }
    @Data
    public static class Geometry {
        @JsonProperty("coordinates")
        private List<List<Double>> coordinates;
        @JsonProperty("type")
        private String type;
    }
}
