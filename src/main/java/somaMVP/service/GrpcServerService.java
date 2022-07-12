package somaMVP.service;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.chb.examples.lib.GreeterGrpc;
import org.chb.examples.lib.HelloReply;
import org.chb.examples.lib.HelloRequest;
import somaMVP.annotation.RunningTime;
import somaMVP.response.ImageResponse;

@GrpcService
@AllArgsConstructor
@Slf4j
public class GrpcServerService extends GreeterGrpc.GreeterImplBase {
    private final FileService fileService;
    private final ImageResponse imageResponse;
    @RunningTime
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        ByteString nameBytes = request.getName();
        byte[] bytes = nameBytes.toByteArray();
        log.info("bytes: {}", bytes);
        fileService.grpcUpload(bytes);
        log.info("요청 {}회 = {}",++imageResponse.sequenceNo, request.getName());
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("sequenceNo : " + imageResponse.sequenceNo)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}