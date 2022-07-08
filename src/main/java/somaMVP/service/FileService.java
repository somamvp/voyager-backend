package somaMVP.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.BinaryMessage;
import somaMVP.annotation.RunningTime;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.List;

import static somaMVP.intercepter.ClearInterceptor.loofCount;

@Service
@NoArgsConstructor
@Slf4j
public class FileService {
    @Value("${attachFileLocation}")
    private String attachFileLocation;
    @Value("${user.home}")
    private String uploadDir;
    private int imageNumber = 0;
    private String originNames;
    @RunningTime
    public void fileUpload(List<byte[]> multipartFile, List<String> originName) {
        if (multipartFile.isEmpty()) {
            log.info("multipartFile = is Empty");
            return ;
        }
        for(byte[] file : multipartFile) {
            originNames = originName.get((imageNumber++)%loofCount);
            Path serverPath = Paths.get(
                    uploadDir +
                            File.separator + // 다른 OS 환경 구분자 호환 용도
                            StringUtils.cleanPath(originNames));
            log.info("[{}번]{} -> {}", imageNumber, attachFileLocation, serverPath.toAbsolutePath());
        try {
            //Files.copy(file.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
            Files.write(serverPath, file); // Inputstream 버퍼로 File.copy를 실행하면 에러가 나기 때문에 write 사용.
        } catch (IOException e) {
            log.error("fail to store file : name={}, exception={}",
                    originNames,
                    e.getMessage());
          }
        }
    }
    @RunningTime
        public void Base64ToImgDecoder(String base64){
        log.info("Base64ToImgDecoder 실행");
            String data = base64;
            String fileName = "socketTest.jpg"; // 파일 이름
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
            try {
                BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
                ImageIO.write(bufImg, "jpg", new File(uploadDir, fileName));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
