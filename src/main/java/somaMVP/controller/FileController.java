package somaMVP.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.service.FileService;

import java.net.http.HttpHeaders;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @PostMapping("/api/v1/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.fileUpload(file);
        return "redirect:/success";
    }
}