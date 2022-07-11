package somaMVP.service;
import io.grpc.stub.StreamObserver;
import io.quarkus.example.HelloReply;
import io.quarkus.example.HelloRequest;
import io.quarkus.example.SimpleGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}