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
    private final ImageResponse imageResponse = new ImageResponse(0);
    private final List<byte[]> multipartFiles = new ArrayList<>(2);
    private final List<String> fileNames = new ArrayList<>(2);
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("img") MultipartFile file) throws IOException {
        multipartFiles.add(file.getInputStream().readAllBytes());
        fileNames.add(file.getOriginalFilename());
        if((++imageResponse.sequenceNo) % 1 == 0){ // % 1 몇 회 요청이 들어왔을 때 업로드를 실행 할지.
            fileService.fileUpload(multipartFiles, fileNames);
            multipartFiles.clear();
            fileNames.clear();
        }
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }
}