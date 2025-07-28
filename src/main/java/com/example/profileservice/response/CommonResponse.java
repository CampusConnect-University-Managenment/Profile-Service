package com.example.profileservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private String message;
    private ResponseStatus status;
    private Object data;
    private int statusCode;
}
