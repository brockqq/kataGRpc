package com.example.client;

//import com.example.auth.AuthServiceGrpc;
//import com.example.auth.CheckTokenRequest;
//import com.example.auth.CheckTokenResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GrpcAuthClient2 {
//
//
//    private final ManagedChannel channel;
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String uri = "http://localhost:8080/api/checkToken";
//
//    public GrpcAuthClient2() {
//        this.channel = ManagedChannelBuilder
//                .forAddress("localhost", 9090)
//                .usePlaintext()
//                .build();
//    }
//
//    // 核心：用虛擬執行緒並行跑 loopTime 次 function
//    public long runParallel(int loopTime, Callable<Long> task) throws Exception {
//        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
//        List<Future<Long>> futures = new ArrayList<>();
//
//        for (int i = 0; i < loopTime; i++) {
//            futures.add(executor.submit(task));
//        }
//
//        long total = 0;
//        for (Future<Long> f : futures) {
//            long aa =f.get();
//            System.out.println(aa);
//            total += aa; // 等待每個請求完成
//        }
//
//        executor.shutdown();
//        return total / loopTime;
//    }
//    public static void main(String[] args) throws Exception {
//        GrpcAuthClient2 client = new GrpcAuthClient2();
//        int loopTime = 1000;
//
//        System.out.println("=== 開始虛擬執行緒測試 ===");
//
////        long grpcAvg = client.runParallel(loopTime, client::runGrpc);
////        System.out.println("gRPC 平均耗時: " + grpcAvg + " ms");
//
//        long restAvg = client.runParallel(loopTime, client::runRestFul);
//        System.out.println("REST 平均耗時: " + restAvg + " ms");
//
//        client.channel.shutdown();
//    }
//    // REST 測試邏輯
//    // REST 用的是 RestTemplate，雖然是 blocking，但它可以完全被虛擬執行緒 takeover，執行效能更好。
//    public long runRestFul() {
//        long start = System.nanoTime();
//
//        Map<String, String> request = new HashMap<>();
//        request.put("token", "VALID_TOKEN");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
//        response.getBody().getBytes(StandardCharsets.UTF_8); // just to simulate size calc
//
//        long end = System.nanoTime();
//        return (end - start) / 1_000_000;
//    }
//
//    // gRPC 測試邏輯
//    // 使用的是 BlockingStub 搭配 Netty channel
//    //Netty 並不直接支援虛擬執行緒（VirtualThread），
//    // 以雖然用 newVirtualThreadPerTaskExecutor()，但每一個 gRPC 呼叫底層仍會切回 native thread 處理 I/O，這造成效能瓶頸。
//    public long runGrpc() {
//        long start = System.nanoTime();
//
//        AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(channel);
//        CheckTokenRequest request = CheckTokenRequest.newBuilder().setToken("VALID_TOKEN").build();
//        CheckTokenResponse response = stub.checkToken(request);
//        //response.toByteArray(); // simulate size calc
//
//        long end = System.nanoTime();
//        return (end - start) / 1_000_000;
//    }
}
