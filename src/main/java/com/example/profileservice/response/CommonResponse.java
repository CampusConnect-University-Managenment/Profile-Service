package com.example.profileservice.response;

import com.example.profileservice.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.profileservice.enumeration.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private String message;
    private ResponseStatus status;
    private Object data;
    private int statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
