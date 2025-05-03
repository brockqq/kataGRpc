package com.example.auth;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void checkToken(CheckTokenRequest request, StreamObserver<CheckTokenResponse> responseObserver) {
        // 模擬 token 驗證
        String token = request.getToken();
        CheckTokenResponse.Builder builder = CheckTokenResponse.newBuilder();

        if ("VALID_TOKEN".equals(token)) {
            builder.setValid(true)
                   .setUserId("user123")
                   .setRole("ADMIN");
        } else {
            builder.setValid(false);
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
