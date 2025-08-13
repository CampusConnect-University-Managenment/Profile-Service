package com.example.profileservice.feign;

import com.example.profileservice.dto.AuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:8088/api/auth")
public interface AuthServiceClient {

    @PostMapping("/register-student")
    void registerStudent(AuthUserRequest request);
    @PutMapping("/update-password/{uniqueId}")
    String updatePasswordInAuth(@PathVariable("uniqueId") String uniqueId,
                                @RequestBody Map<String, String> request);
}
