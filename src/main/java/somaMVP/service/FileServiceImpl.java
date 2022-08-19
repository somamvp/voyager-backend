package somaMVP.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.annotation.RunningTime;
import somaMVP.response.ImageResponse;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    public final FileInferenceService fileInferenceService;
    public static final Integer FILE_LOOP_COUNT = 1; // % 몇 회 요청이 들어왔을 때 업로드 메소드를 수행 할지 셋팅.
    protected final ConcurrentHashMap<String, byte[]> fileMaps = new ConcurrentHashMap<>();

    @Value("${attachFileLocation}")
    public String attachFileLocation;
    @Value("${uploadDir}")
    public String uploadDir;
    private int imageNumber = 0;
    private final ImageResponse imageResponse;
    @RunningTime
    public void fileProcess(MultipartFile file){
        Optional<String> fileNames = Optional.ofNullable(file.getOriginalFilename());
        String filename = fileNames.orElse("test.jpg");
        try{
            byte[] fileByte = file.getBytes();
            Path path = Paths.get(uploadDir + filename);
            fileMaps.put(filename,fileByte);
            if((imageResponse.sequenceNo) % FILE_LOOP_COUNT == 0){
                httpUpload(fileMaps, path); // 파일 업로드 수행
                fileMaps.clear();
            }
        }catch (IOException e){
            log.error("파일 업로드 실패 {}", e.toString());
        }

    }
    @RunningTime
    public void httpUpload(ConcurrentHashMap<String, byte[]> fileMaps, Path serverPath) {
        fileMaps.forEach((fileName, fileByte)->{
            defaultUpload(fileByte, serverPath);
            log.info("[{}번]{} -> {}",++imageNumber, attachFileLocation, serverPath.toAbsolutePath());
        });
    }
    @RunningTime
        public byte[] base64ToImgDecoder(String data){
            log.info("Base64ToImgDecoder 실행");
            return Base64.getDecoder().decode(data); // Base64 데이터를 byte 배열로 변환
    }
    @RunningTime
    public void grpcUpload(byte[] imageBytes, Path serverPath){
        log.info("grpcUpload 실행");
        defaultUpload(imageBytes, serverPath);
    }

    public void defaultUpload(byte[] imageBytes, Path serverPath){
        if (imageBytes.length == 0) {
            log.info("imageBytes = is Empty");
        }
        try {
            Files.write(serverPath, imageBytes);
        } catch (IOException e) {
            log.info("IOException = {}", e);
        }
    }
}
