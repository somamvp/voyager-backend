package somaMVP.domain.gps;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Tag(name = "file", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/gps")
public class GpsController {
    public final GpsService gpsService;
    @GetMapping("/create")
    public String createGps(HttpServletResponse response) {
        String gpsId = gpsService.createGps();
        Cookie cookie = new Cookie("gpsId", gpsId);
        response.addCookie(cookie);
        return gpsId;
    }

    @PostMapping("/update") // TODO redis pub/sub 기능으로 리팩토링
    public String gpsReport(@CookieValue(name = "gpsId", required = false) String gpsId, @RequestBody @Valid UserGpsDto userGpsDto) {
        if(gpsId == null) {
            return "redirect:/api/v1/gps/create";
        }
        log.info("POST /report 요청");
        log.info("session id : " + gpsId);
        return gpsService.updateGps(gpsId, userGpsDto);
    }

    @PostMapping("/expire")
    public String logout(HttpServletResponse response, String gpsId) {
        Cookie cookie = new Cookie(gpsId, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
