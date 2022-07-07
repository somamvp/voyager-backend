package stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import somaMVP.response.ImageResponse;

@Profile("stomp")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/upload")
    public void enter(ImageResponse imageResponse) {
        imageResponse.sequenceNo++;
        sendingOperations.convertAndSend("/upload",imageResponse.getSequenceNo());
    }
}