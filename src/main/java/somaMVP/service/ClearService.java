package somaMVP.service;

import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Setter
@Component
public class ClearService {
    public static final int FILE_ROOF_COUNT = 1; // % 몇 회 요청이 들어왔을 때 업로드 메소드를 수행 할지 셋팅.
    protected static List<byte[]> multipartFiles = new ArrayList<>(FILE_ROOF_COUNT);
    protected static List<String> fileNames = new ArrayList<>(FILE_ROOF_COUNT);

    public void clearList(List<byte[]> multipartFiles, List<String>fileNames) {
        for (Iterator<byte[]> it = multipartFiles.iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
        for (Iterator<String> it = fileNames.iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
    }
}
