package com.example.client;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomDataGenerator {
    public static String getTestString(){
        return generateRandomString(512*1024);
    }
    public static String generateRandomString(int sizeInBytes) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(sizeInBytes);
        for (int i = 0; i < sizeInBytes; i++) {
            // ASCII 中可顯示字元（32~126）
            char c = (char) (random.nextInt(95) + 32);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int size = 4 * 1024 * 1024; // 4MB
        String data = generateRandomString(size);
        System.out.println("資料長度（byte）: " + data.getBytes(StandardCharsets.UTF_8).length);
    }
}
