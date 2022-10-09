package somaMVP.domain.gps;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import somaMVP.domain.gis.GisDtos;
import somaMVP.domain.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Tag(name = "gps", description = "유저 GPS API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/gps")
public class GpsController {
    private final GpsService gpsService;
    @GetMapping("/create")
    public String createGps(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String gpsId = gpsService.createGps(session.getId());
        session.setAttribute(SessionConst.GPS_ID, gpsId);
        log.info("create sessionId: {}", gpsId);
        return gpsService.createGps(gpsId); // null이면 안됨.
    }

    // SessionAttribute를 사용하면 세션에 저장된 값을 파라미터로 받을 수 있음. 대신 새로 생성하지는 않음.
    @PostMapping("/update") // TODO redis pub/sub 기능으로 리팩토링
    public String updateGps(@SessionAttribute(name = SessionConst.GPS_ID, required = false) String gpsId,
                            @RequestBody @Valid UserUpdateGpsDto updateGpsDto) {
        return gpsService.updateGps(gpsId, updateGpsDto);
    }


    @PostMapping("/report")
    public GisDtos reportGps(@RequestBody @Valid UserGpsDto userGpsDto) {
        Double distance = userGpsDto.getDistance();
        if(distance == null) {
//            distance = 0.3;
        }
        distance = 0.015;
        log.debug("GPSX: {}, GPSY: {}", userGpsDto.getGpsX(), userGpsDto.getGpsY());
        return gpsService.nearZebra(userGpsDto.getGpsY(), userGpsDto.getGpsX(), distance);
    }
    @DeleteMapping("/expire")
    public String expire(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getId();
        session.invalidate();
        return gpsService.deleteGps(session.getId());
    }


    @GetMapping("/direction")
    public String getDirection(@SessionAttribute(name = SessionConst.GPS_ID, required = false) String gpsId) {
        return gpsService.getDirection(gpsId);
    }
}
