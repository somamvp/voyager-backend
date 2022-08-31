package somaMVP.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import somaMVP.domain.Member;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileServiceImpl;
import somaMVP.service.FileInferenceService;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.*;

@Tag(name = "file", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/api/files")
public class FileUploadController {
    public final FileServiceImpl fileService;
    public final ImageResponse imageResponse;
    public final FileInferenceService fileInferenceService;
    @PostMapping("/upload")
    public ImageResponse uploadFile(@RequestParam("img") MultipartFile file){
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        fileService.fileProcess(file);
        return imageResponse;
    }

    @PostMapping("/ml/upload")
    public Mono<String> inference(@RequestParam("img") MultipartFile file,
                                  @RequestParam String userId) {
        Mono<String> inferenceResult = fileInferenceService.mlUpload(file, userId);
        log.info("inference result: {}", inferenceResult);
        return requireNonNullElseGet(inferenceResult, () -> Mono.just("null error"));
    }

    @GetMapping("/responseTest")
    public String test() {
        return fileInferenceService.test();
    }
}