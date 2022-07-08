package somaMVP.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import somaMVP.response.ImageResponse;
import somaMVP.service.FileService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileSocketController {

    private final SimpMessageSendingOperations sendingOperations;
    private final FileService fileService;
    public final ImageResponse imageResponse;

    @MessageMapping("/img")
    public void binary(BinaryMessage message) {
        log.info("Socket /upload 요청 {} 받음", ++imageResponse.sequenceNo);
        fileService.handleBinaryMessage(message);
        sendingOperations.convertAndSend("/sub/upload", imageResponse);
    }
    @MessageMapping("/upload")
    public void upload(ImageResponse img) {
        log.info("Socket /upload 요청 {} 받음", ++imageResponse.sequenceNo);
        sendingOperations.convertAndSend("/sub/upload", imageResponse);
    }
}
