package somaMVP.domain.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data

public class YoloDto {

    private String warning;
    private String description;
    private List<String> guide;
    private Position position;
    @JsonProperty("yolo_objects")
    private List<YoloObjects> yoloObjects;
    @JsonProperty("rgb_shape")
    private List<Integer> rgbShape;
    private boolean isDepth;

    public YoloDto(String warning, String description, List<String> guide, Position position, List<YoloObjects> yoloObjects, List<Integer> rgbShape, boolean isDepth) {
        this.warning = warning;
        this.description = description;
        this.guide = guide;
        this.position = position;
        this.yoloObjects = yoloObjects;
        this.rgbShape = rgbShape;
        this.isDepth = isDepth;
    }


    @Data
    public static class Position {
        private double speed;
        private double heading;
        private double y;
        private double x;
    }
    @Data
    public static class YoloObjects {
        private double depth;
        private String name;
        private double cls;
        private double confidence;
        private double ymax;
        private double xmax;
        private double ymin;
        private double xmin;
    }
}
