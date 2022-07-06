package somaMVP.intercepter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import somaMVP.service.FileService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Setter
public class ClearInterceptor {
    public static int loofCount = 1; // % 몇 회 요청이 들어왔을 때 업로드 메소드를 수행 할지 셋팅.
    public final List<byte[]> multipartFiles = new ArrayList<>(loofCount);
    public final List<String> fileNames = new ArrayList<>(loofCount);

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
