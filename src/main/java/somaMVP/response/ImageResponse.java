package somaMVP.response;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class ImageResponse {
    public int sequenceNo;
}

//upload file 요청시 이미지 업로드 수행 시점에 대한 정보를 저장하는 클래스.