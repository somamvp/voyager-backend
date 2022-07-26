package somaMVP.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileService;
import java.io.IOException;

@Tag(name = "upload", description = "파일 업로드 API")
@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileRestController {
    public final FileService fileService;
    public final ImageResponse imageResponse;
    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        fileService.fileProcess(file);
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }
}