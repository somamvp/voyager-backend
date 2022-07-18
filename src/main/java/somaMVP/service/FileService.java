package somaMVP.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.annotation.RunningTime;
import somaMVP.response.ImageResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.List;

import static somaMVP.service.ClearService.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {
    @Value("${attachFileLocation}")
    public String attachFileLocation;
    @Value("${HOME}")
    public String uploadDir;
    private int imageNumber = 0;
    private final ClearService clearService;
    private final ImageResponse imageResponse;
    Path serverPath = Paths.get(
            File.separator + "home" + File.separator + "juwon" +
                    File.separator + // 다른 OS 환경 구분자 호환 용도
                    StringUtils.cleanPath("test.jpg"));
    @RunningTime
    public void httpUpload(List<byte[]> multipartFile, List<String> originName) {
        for(byte[] imageBytes : multipartFile) {
            //String originNames = originName.get((imageNumber++)%loofCount); // Multipart 파일 이름 읽어올 때 사용
            defaultUpload(imageBytes);
            log.info("[{}번]{} -> {}",++imageNumber, attachFileLocation, serverPath.toAbsolutePath());
        }
    }
    @RunningTime
    public void fileProcess(MultipartFile file) throws IOException {
        multipartFiles.add(file.getInputStream().readAllBytes());
        fileNames.add(file.getOriginalFilename());
        if((imageResponse.sequenceNo) % FILE_ROOF_COUNT == 0){
            httpUpload(multipartFiles, fileNames); // 파일 업로드 수행
            clearService.clearList(multipartFiles, fileNames); // 업로드 후 리스트 초기화
        }
    }
    @RunningTime
        public void base64ToImgDecoder(String data){
            log.info("Base64ToImgDecoder 실행");
            byte[] imageBytes = Base64.getDecoder().decode(data); // Base64 데이터를 byte 배열로 변환
            defaultUpload(imageBytes);
    }

    @RunningTime
    public void grpcUpload(byte[] imageBytes){
        log.info("grpcUpload 실행");
        defaultUpload(imageBytes);
    }

    public void defaultUpload(byte[] imageBytes){
        if (imageBytes.length == 0) {
            log.info("imageBytes = is Empty");
            return ;
        }
        try {
            Files.write(serverPath, imageBytes);
            log.info("path = {}", serverPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IOException = {}", e.getMessage());
        }
    }
}
