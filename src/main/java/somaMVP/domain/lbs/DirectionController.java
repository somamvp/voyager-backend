package somaMVP.domain.lbs;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/lbs")
public class DirectionController {
    public final DirectionService directionService;
    public final SignalRepository signalRepository;

    @PostMapping("/getDirections")
    public Object getDirection(@RequestBody DirectionDto directionDto) {
        HashMap<String, Object> direction = (HashMap<String, Object>) directionService.getDirection(directionDto);
        List<Object> featuresList = (List<Object>) direction.get("features");
        Gson gson = new Gson();

        for (int i = 0; i < featuresList.size(); i++) {
            if(i % 2 != 0){
                Object o = featuresList.get(i);
                LineDto lineDto = gson.fromJson(gson.toJson(o), LineDto.class);
                Optional<String> pathType = Optional.ofNullable(lineDto.getProperties().getPathType());
                pathType.ifPresentOrElse(
                        s -> {
                            if (s.equals("1")) {
                                MyDouble myDouble = new MyDouble();
                                lineDto.getGeometry().getCoordinates().forEach(
                                        coordinates -> coordinates.forEach(
                                                coordinate -> {
                                                    int intValue = coordinate.intValue();

                                                    if(intValue == 126 || intValue == 127 || intValue == 128){
                                                        myDouble.sumX += coordinate;
                                                    }else{
                                                        myDouble.sumY += coordinate;
                                                    }
                                                }
                                        )
                                );
                                log.info("횡단보도");
                                log.info("x : " + String.format("%.14f", myDouble.sumX / 2));
                                log.info("y : " + String.format("%.14f", myDouble.sumY / 2));
                            }
                        },
                        () -> log.info("신호등 유무 알수 없음"));
            }
        }
        return direction;
    }
}

@Data
@NoArgsConstructor
class MyDouble{
    public Double sumX = 0.0;
    public Double sumY = 0.0;
}