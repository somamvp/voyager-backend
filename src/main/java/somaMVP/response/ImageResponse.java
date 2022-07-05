package somaMVP.response;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class ImageResponse {
    public int sequenceNo;

    public ImageResponse(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
