package somaMVP.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import somaMVP.annotation.RunningTime;
import somaMVP.response.ImageResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class FileService {
    @Value("${attachFileLocation}")
    private String attachFileLocation;

    @Value("${user.home}")
    private String uploadDir;
    public int count = 0;
    public String originNames;
    @RunningTime
    public void fileUpload(List<byte[]> multipartFile, List<String> originName) {
        if (multipartFile.isEmpty()) {
            log.info("multipartFile = is Empty");
            return ;
        }
        for(byte[] file : multipartFile) {
            originNames = originName.get((count++)%1);
            Path serverPath = Paths.get(
                    uploadDir +
                            File.separator +
                            StringUtils.cleanPath(originNames));
            log.info("[{}번]ath : {} \n {}", count, serverPath, serverPath.toAbsolutePath());

        try {
            //Files.copy(file.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
            Files.write(serverPath, file);
        } catch (IOException e) {
            log.error("fail to store file : name={}, exception={}",
                    originNames,
                    e.getMessage());
            //return getErrorResponse("[Error] File IOException");
          }
        }
    }
}
