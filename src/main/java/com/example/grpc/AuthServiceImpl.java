package com.example.grpc;

import com.example.auth.AuthServiceGrpc;
import com.example.auth.CheckTokenRequest;
import com.example.auth.CheckTokenResponse;
import com.example.client.RandomDataGenerator;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {
    @Override
    public void checkToken(CheckTokenRequest request, StreamObserver<CheckTokenResponse> responseObserver) {
        String token = request.getToken();
        CheckTokenResponse.Builder builder = CheckTokenResponse.newBuilder();
        if ("VALID_TOKEN".equals(token)) {
            builder.setValid(true).setUserId("user123").setRole(RandomDataGenerator.getTestString());
        } else {
            builder.setValid(false);
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
    private static  final String tmp ="1qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx12";
}
