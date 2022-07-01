package somaMVP.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.annotation.RunningTime;
import somaMVP.response.ImageResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static somaMVP.controller.BaseRestController.*;

@Service
@NoArgsConstructor
@Slf4j
public class FileService {

    @Value("${attachFileLocation}")
    private String attachFileLocation;

    @Value("${user.home}")
    private String uploadDir;
    public int count = 0;
    //public ImageResponse imageResponse = new ImageResponse(0);
    @RunningTime
    public void fileUpload(MultipartFile multipartFile) {
        if (multipartFile == null) {
            log.info("create: multipartFile=null");
            //return getErrorResponse("[Error] Fail to save a multipartFile.");
        }

        Path serverPath = Paths.get(
                uploadDir +
                        File.separator +
                        StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        log.info("[{}번]ath : {} \n {}", ++count, serverPath.toString(), serverPath.toAbsolutePath());

        try {
            Files.copy(multipartFile.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
            multipartFile.getOriginalFilename();
            Resource resource = multipartFile.getResource();
            //imageResponse.setSequenceNo(count);
            // imageResponse.getSequenceNo() "sequenceNo": Int = 3333 Body의 값
        } catch (IOException e) {
            log.error("fail to store file : name={}, exception={}",
                    multipartFile.getOriginalFilename(),
                    e.getMessage());
            //return getErrorResponse("[Error] File IOException");
        }
    }
}
