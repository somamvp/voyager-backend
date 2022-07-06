package somaMVP.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.intercepter.ClearInterceptor;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileService;

import java.io.IOException;

import static somaMVP.intercepter.ClearInterceptor.loofCount;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileRestController {
    private final FileService fileService;
    public final ImageResponse imageResponse = new ImageResponse(0);
    public ClearInterceptor cleanInter = new ClearInterceptor();
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        cleanInter.multipartFiles.add(file.getInputStream().readAllBytes());
        cleanInter.fileNames.add(file.getOriginalFilename());
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        if((imageResponse.sequenceNo) % loofCount == 0){
            fileService.fileUpload(cleanInter.multipartFiles, cleanInter.fileNames); // 파일 업로드 수행
            cleanInter.clearList(cleanInter.multipartFiles, cleanInter.fileNames); // 업로드 후 리스트 초기화
        }
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }
}