package somaMVP.domain.gps;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Tag(name = "gps", description = "유저 GPS API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/gps")
public class GpsController {
    public final GpsService gpsService;
    @GetMapping("/create")
    public String createGps(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String gpsId = gpsService.createGps(session.getId());
        session.setAttribute(SessionConst.GPS_ID, gpsId);
        return gpsService.createGps(gpsId); // null이면 안됨.
    }

    // SessionAttribute를 사용하면 세션에 저장된 값을 파라미터로 받을 수 있음. 대신 새로 생성하지는 않음.
    @PostMapping("/update") // TODO redis pub/sub 기능으로 리팩토링
    public String gpsReport(@SessionAttribute(name = SessionConst.GPS_ID, required = false) String gpsId,
                            @RequestBody @Valid UserGpsDto userGpsDto) {
        return gpsService.updateGps(gpsId, userGpsDto);
    }

    @DeleteMapping("/expire")
    public String expire(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();
        session.invalidate();
        return gpsService.deleteGps(id);
    }


    @GetMapping("/direction")
    public String getDirection(@SessionAttribute(name = SessionConst.GPS_ID, required = false) String gpsId) {
        return gpsService.getDirection(gpsId);
    }
}
