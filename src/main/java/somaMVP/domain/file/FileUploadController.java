package somaMVP.domain.file;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import somaMVP.domain.gps.GpsService;
import somaMVP.domain.session.SessionConst;
import somaMVP.response.ImageResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Tag(name = "file", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/files")
public class FileUploadController {
    public final FileServiceImpl fileService;
    private final FileInferenceService inferenceService;
    public final GpsService gpsService;
    public final ImageResponse imageResponse;
    public final FileInferenceService fileInferenceService;

    @GetMapping("/create")
    public String sessionCreate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String gpsId = gpsService.createGps(session.getId());
        session.setAttribute(SessionConst.GPS_ID, gpsId);
        Object create = inferenceService.create();
        gpsService.saveRedisState(gpsId, new Gson().toJson(create));
        log.info("create sessionId: {}", gpsId);
        return gpsId; // null이면 안됨.
    }

    @PostMapping("/upload")
    public ImageResponse uploadFile(@RequestParam("source") MultipartFile file){
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        fileService.fileProcess(file);
        return imageResponse;
    }

    @PostMapping("/ml/upload")
    public Object inference(HttpServletRequest request,
                                  @RequestParam("source") MultipartFile file, @RequestParam(value = "is_rot", required = false) Boolean isRotate,
                                  @RequestParam(value = "gps_info", required = false) String gpsInfo, @RequestParam(value = "cross_start", required = false) Boolean crossStart,
                                  @RequestParam(value = "should_light_exist", required = false) Boolean shouldLightExist) {
        // cross_start boolean 없을 수 있음.
        // should_light_exist

        // 세션 아이디 없으면 생성, 있으면 가져오기
        HttpSession session = request.getSession();
        String gpsId = gpsService.createGps(session.getId());
        session.setAttribute(SessionConst.GPS_ID, gpsId);

        // ML inference 호출
        Mono<Object> inferenceResult = fileInferenceService.mlUpload(gpsId, file, isRotate, gpsInfo, crossStart, shouldLightExist);
        assert inferenceResult != null;

        // ML inference 결과를 배열로 변환
        List<String> block = (List<String>) inferenceResult.block();
        assert block != null;

        // ML inference 결과 반환
        Object appResult = block.get(0); // app 리턴 값.
        Object yoloResult = block.get(1); // yolo 결과 로깅
        Object stateRedis = block.get(2); // redis에 저장할 상태

        // ML inference 결과 로깅 및 Redis 저장
        String yoloResultString = new Gson().toJson(yoloResult);
        String stateResultString = new Gson().toJson(stateRedis);
        log.info("inferenceResult: {}", yoloResultString);
        gpsService.saveRedisState(gpsId, stateResultString);
        return appResult;
    }

    @GetMapping("/test")
    public String test() {
        return fileInferenceService.test();
    }

    @GetMapping("/logging")
    public LogExample logging(@RequestBody LogExample logExample) {
        log.info("inferenceResult: {}", new Gson().toJson(logExample));
        return logExample;
    }
}