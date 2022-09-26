package somaMVP.service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;


public interface FileService {
    void httpUpload(ConcurrentHashMap<String, byte[]> fileMaps, Path serverPath);
    void fileProcess(MultipartFile file) throws IOException;
    byte[]  base64ToImgDecoder(String data);
    void grpcUpload(byte[] imageBytes, Path serverPath);
    void defaultUpload(byte[] imageBytes, Path serverPath);
}
