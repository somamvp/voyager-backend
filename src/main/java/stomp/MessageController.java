package stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import somaMVP.response.ImageResponse;

@Profile("stomp")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/upload")
    public void enter(Object obj) {
        log.info("request = {}", obj);
        //imageResponse.sequenceNo++;
        //sendingOperations.convertAndSend("/upload",imageResponse.getSequenceNo());
        sendingOperations.convertAndSend("/upload",obj);
    }
}