package somaMVP.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import somaMVP.service.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {
    @Value("${attachFileLocation}")
    private String attachFileLocation;
    @Value("${user.home}")
    public String FILE_UPLOAD_PATH;

    private static List<WebSocketSession> list = new ArrayList<>();
    public final FileService fileService;

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer byteBuffer = message.getPayload();
        String fileName = "socketTest.jpg";
        File dir = new File(FILE_UPLOAD_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(FILE_UPLOAD_PATH, fileName);
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            byteBuffer.flip(); //byteBuffer를 읽기 위해 세팅
            out = new FileOutputStream(file, true); //생성을 위해 OutputStream을 연다.
            outChannel = out.getChannel(); //채널을 열고
            byteBuffer.compact(); //파일을 복사한다.
            outChannel.write(byteBuffer); //파일을 쓴다.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        list.add(session);

        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info(session + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
