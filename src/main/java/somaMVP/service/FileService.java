package somaMVP.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import somaMVP.annotation.RunningTime;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class FileService {
    @Value("${attachFileLocation}")
    private String attachFileLocation;
    @Value("${user.home}")
    private String uploadDir;
    private int imageNumber = 0;
    Path serverPath = Paths.get(
            File.separator + "home" + File.separator + "ec2-user" +
                    File.separator + // 다른 OS 환경 구분자 호환 용도
                    StringUtils.cleanPath("test.jpg"));
    @RunningTime
    public void fileUpload(List<byte[]> multipartFile, List<String> originName) {
        for(byte[] imageBytes : multipartFile) {
            //String originNames = originName.get((imageNumber++)%loofCount); // Multipart 파일 이름 읽어올 때 사용
            log.info("[{}번]{} -> {}", ++imageNumber, attachFileLocation, serverPath.toAbsolutePath());
            defaultUpload(imageBytes);
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
            log.info("IOException = {}", e.getMessage());
        }
    }
}
