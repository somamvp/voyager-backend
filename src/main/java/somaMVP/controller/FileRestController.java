package somaMVP.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileServiceImpl;
import somaMVP.service.FluxInferenceService;

import java.io.IOException;

import static java.util.Objects.*;

@Tag(name = "upload", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileRestController {
    public final FileServiceImpl fileService;
    public final ImageResponse imageResponse;
    public final FluxInferenceService fluxInferenceService;
    @PostMapping("/upload")
    public ImageResponse uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        fileService.fileProcess(file);
        return imageResponse;
    }

    @PostMapping("/ml/upload")
    public Mono<String> mlUploadFile(@RequestParam("img") MultipartFile file){
        Mono<String> uploadResult = fluxInferenceService.mlUpload(file);
        return requireNonNullElseGet(uploadResult, () -> Mono.just("null error"));
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("ok");
    }
}