package somaMVP.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileRestController {
    private final FileService fileService;
    public final ImageResponse imageResponse = new ImageResponse(0);
    public static int loofCount = 2; // % 몇 회 요청이 들어왔을 때 업로드 메소드를 수행 할지 셋팅.
    private final List<byte[]> multipartFiles = new ArrayList<>(loofCount);
    private final List<String> fileNames = new ArrayList<>(loofCount);
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        multipartFiles.add(file.getInputStream().readAllBytes());
        fileNames.add(file.getOriginalFilename());
        log.info("POST /upload 요청 {}회 받음", ++imageResponse.sequenceNo);
        if((imageResponse.sequenceNo) % loofCount == 0){
            fileService.fileUpload(multipartFiles, fileNames); // 파일 업로드 수행
            multipartFiles.clear(); // 버퍼 비우기
            fileNames.clear(); // 버퍼 비우기
        }
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }
}