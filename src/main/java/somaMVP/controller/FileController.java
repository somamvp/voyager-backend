package somaMVP.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.service.FileService;

import java.net.http.HttpHeaders;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    public String index() {
        return "redirect:/upload";
    }
    @GetMapping("/upload")
    public String upload() {
        return "content/upload";
    }
    @GetMapping("/success")
    public String success() {
        return "파일 저장 완료!";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.fileUpload(file);
        return "redirect:/success";
    }
}