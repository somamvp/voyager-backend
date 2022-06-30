package somaMVP.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@NoArgsConstructor
@Slf4j
public class FileService {

    @Value("${attachFileLocation}")
    private String attachFileLocation;

    @Value("${user.home}")
    private String uploadDir;
    public void fileUpload(MultipartFile multipartFile) {
        Path serverPath = Paths.get(
                uploadDir +
                        File.separator +
                        StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        log.info("path : {} \n {}",serverPath.toString(),serverPath.toAbsolutePath());
        try {
            Files.copy(multipartFile.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("fail to store file : name={}, exception={}",
                    multipartFile.getOriginalFilename(),
                    e.getMessage());
        }
    }
}
