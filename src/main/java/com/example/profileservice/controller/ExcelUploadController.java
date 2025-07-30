package com.example.profileservice.controller;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.response.CommonResponse;
import com.example.profileservice.service.StudentService;
import com.example.profileservice.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.profileservice.enumeration.ResponseStatus;

import java.util.List;


@RestController
@RequestMapping("/api/admin/excel")

public class ExcelUploadController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/upload")
    public ResponseEntity<CommonResponse> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<StudentEntity> students = ExcelHelper.convertExcelToStudentList(file);
            List<StudentEntity> savedStudents = studentService.saveAllStudents(students);

            CommonResponse response = new CommonResponse();
            response.setData(savedStudents);
            response.setMessage("Excel data uploaded and saved successfully");
            response.setStatusCode(201);
            response.setStatus(ResponseStatus.SUCCESS);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace(); // Optional: for debugging
            CommonResponse response = new CommonResponse();
            response.setStatusCode(500);
            response.setMessage("Failed to upload Excel file");
            response.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
