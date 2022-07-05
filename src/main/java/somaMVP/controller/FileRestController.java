package somaMVP.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileService;

import javax.sound.midi.Sequence;
import java.awt.*;
import java.net.http.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {
    @Autowired
    private final FileService fileService;
    private int cnt = 0;
    @GetMapping
    public String index() {
        return "redirect:/upload";
    }
    @GetMapping("/upload")
    public String upload() {
        return "content/upload";
    }
    @GetMapping("/success")
    public String success() { return "redirect:";}
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("img") MultipartFile file) {
        this.fileService.fileUpload(file);
        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("sequenceNo", ++cnt);
        return rtn;
    }
}