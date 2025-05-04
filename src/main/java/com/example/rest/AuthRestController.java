package com.example.rest;

import com.example.client.RandomDataGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    private static  final String tmp ="1qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx121qaz2wsx12";

    @PostMapping("/checkToken")
    public ResponseEntity<AuthResponse> checkToken(@RequestBody AuthRequest request) {
        if ("VALID_TOKEN".equals(request.getToken())) {
            return ResponseEntity.ok(new AuthResponse(true, "user123", RandomDataGenerator.getTestString()));
        }
        return ResponseEntity.ok(new AuthResponse(false, null, null));
    }
}

class AuthRequest {
    private String token;
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

class AuthResponse {
    private boolean valid;
    private String userId;
    private String role;

    public AuthResponse(boolean valid, String userId, String role) {
        this.valid = valid;
        this.userId = userId;
        this.role = role;
    }

    public boolean isValid() { return valid; }
    public String getUserId() { return userId; }
    public String getRole() { return role; }
}
