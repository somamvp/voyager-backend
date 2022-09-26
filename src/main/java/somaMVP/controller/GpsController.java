package somaMVP.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "file", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/gps")
public class GpsController {
    @PostMapping("/report")
    public String gpsReport(@RequestPart("gps") String gps, @RequestParam("session_id") Long session_id){
        log.info("POST /report 요청");
//        return GpsService.findByGps(gps);
        return "gps";
    }
}
