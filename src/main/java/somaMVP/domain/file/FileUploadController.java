package somaMVP.domain.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import somaMVP.response.ImageResponse;

import static java.util.Objects.requireNonNullElseGet;

@Tag(name = "file", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/files")
public class FileUploadController {
    public final FileServiceImpl fileService;
    public final ImageResponse imageResponse;
    public final FileInferenceService fileInferenceService;
    @PostMapping("/upload")
    public ImageResponse uploadFile(@RequestParam("source") MultipartFile file){
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        fileService.fileProcess(file);
        return imageResponse;
    }

    @PostMapping("/ml/upload")
    public Mono<String> inference(@RequestParam("source") MultipartFile file) {
        Mono<String> inferenceResult = fileInferenceService.mlUpload(file);
        log.info("inference result: {}", inferenceResult);
        return requireNonNullElseGet(inferenceResult, () -> Mono.just("/ml/upload null error"));
    }

    @GetMapping("/test")
    public String test() {
        return fileInferenceService.test();
    }
}