
package com.example.client;

import com.example.auth.AuthServiceGrpc;
import com.example.auth.CheckTokenRequest;
import com.example.auth.CheckTokenResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncGrpcAuthClient {

    private final ManagedChannel channel;
    private final AuthServiceGrpc.AuthServiceStub asyncStub;

    public AsyncGrpcAuthClient() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.asyncStub = AuthServiceGrpc.newStub(channel);
    }

    public void shutdown() {
        channel.shutdown();
    }

    public long runAsyncGrpc() throws InterruptedException {
        CheckTokenRequest request = CheckTokenRequest.newBuilder()
                .setToken("VALID_TOKEN")
                .build();

        CountDownLatch latch = new CountDownLatch(1);
        long start = System.nanoTime();

        asyncStub.checkToken(request, new StreamObserver<CheckTokenResponse>() {
            @Override
            public void onNext(CheckTokenResponse value) {
                // System.out.println("Received: " + value.getValid());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("gRPC Error: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        latch.await(5, TimeUnit.SECONDS); // 等待最多 5 秒完成
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }

    public static void main(String[] args) throws Exception {
        AsyncGrpcAuthClient client = new AsyncGrpcAuthClient();
        int loopTime = 10000;
        long total = 0;

        System.out.println("=== Async gRPC with StreamObserver ===");

        for (int i = 0; i < loopTime; i++) {
            long ms = client.runAsyncGrpc();
            total += ms;
            if (i % 100 == 0) {
                System.out.println("[" + i + "] Response time: " + ms + " ms");
            }
        }

        System.out.println("平均耗時: " + (total / loopTime) + " ms");
        client.shutdown();
    }
}
