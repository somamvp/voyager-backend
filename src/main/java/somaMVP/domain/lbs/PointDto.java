package somaMVP.domain.lbs;

import lombok.Data;

import java.util.List;

@Data
public class PointDto {

    private String id;
    private Properties properties;
    private Geometry geometry;
    private String type;
    @Data
    public static class Properties {
        private int index;
        private int totaltime;
        private double totallength;
        private int srid;
    }
    @Data
    public static class Geometry {
        private List<Double> coordinates;
        private String type;
    }
}
