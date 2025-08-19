package com.example.profileservice.feign;

import com.example.profileservice.dto.AuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:8088/api/auth")
public interface AuthServiceClient {

    @PostMapping("/register-user")
    void registerStudent(AuthUserRequest request);

    @DeleteMapping("/delete-user/{uniqueId}")
    String deleteUserInAuth(@PathVariable("uniqueId") String uniqueId);

}
