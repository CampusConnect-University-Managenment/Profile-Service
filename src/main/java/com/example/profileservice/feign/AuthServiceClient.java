package com.example.profileservice.feign;

import com.example.profileservice.dto.AuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", url = "http://localhost:8088/api/auth")
public interface AuthServiceClient {

    @PostMapping("/register-student")
    void registerStudent(AuthUserRequest request);
}
