package somaMVP.domain.lbs;

import com.google.gson.Gson;
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
        HashMap<Object, Object> resultMap = new HashMap<>();
        Object type = direction.get("type");
        List<Object> featuresList = (List<Object>) direction.get("features");
        Gson gson = new Gson();

        for (int i = 0; i < featuresList.size(); i++) {
            if(i % 2 != 0){
                Object o = featuresList.get(i);
                LineDto lineDto = gson.fromJson(gson.toJson(o), LineDto.class);
                Optional<String> pathType = Optional.ofNullable(lineDto.getProperties().getPathType());
                int finalI = i;
                pathType.ifPresentOrElse(
                        s -> {
                            if (s.equals("1")) {
                                double length = lineDto.getProperties().getLength();
                                Optional<Signal> byId = signalRepository.findById(String.valueOf(length));

                                byId.ifPresent(signal -> {
                                    if (signal.getPedSig() == 1) {
                                        log.info("신호등 있는 횡단보도");
                                        lineDto.getProperties().setPedSignal("1");

                                    } else if(signal.getPedSig() == 0) {
                                        log.info("신호등 없는 횡단보도");
                                        lineDto.getProperties().setPedSignal("0");
                                    }
                                });
                                featuresList.set(finalI, lineDto);
                            }
                        },
                        () -> log.info("신호등 유무 알수 없음"));
            }
        }
        resultMap.put("type", type);
        resultMap.put("features", featuresList);
        return resultMap;
    }
}