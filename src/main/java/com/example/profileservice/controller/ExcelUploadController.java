package com.example.profileservice.controller;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.response.CommonResponse;
import com.example.profileservice.service.StudentService;
import com.example.profileservice.service.impl.EmailService;
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

    @Autowired
    private EmailService emailService;

    @PostMapping("/upload")
    public ResponseEntity<CommonResponse> uploadExcel(@RequestParam("file") MultipartFile file) {
        CommonResponse response = new CommonResponse();
        try {
            if (file.isEmpty()) {
                response.setStatusCode(400);
                response.setMessage("Uploaded file is empty");
                response.setStatus(ResponseStatus.FAILED);
                return ResponseEntity.badRequest().body(response);
            }

            // Step 1: Convert Excel to Student List
            List<StudentEntity> students = ExcelHelper.convertExcelToStudentList(file);

            if (students == null || students.isEmpty()) {
                response.setStatusCode(400);
                response.setMessage("Excel file has no valid student records");
                response.setStatus(ResponseStatus.FAILED);
                return ResponseEntity.badRequest().body(response);
            }

            // Step 2: Save to DB
            List<StudentEntity> savedStudents = studentService.saveAllStudents(students);

            // Step 3: Send Emails
            for (StudentEntity student : savedStudents) {
                try {
                    String personalEmail = student.getStudentEmail();
                    String rollNo = student.getStudentRollNo();
                    String officialEmail = rollNo + "@university.edu";
                    String staticPassword = "Student@123";

                    emailService.sendStudentCredentials(personalEmail, officialEmail, staticPassword);
                } catch (Exception emailEx) {
                    System.err.println("Email failed for " + student.getStudentEmail() + ": " + emailEx.getMessage());
                }
            }

            // Step 4: Respond
            response.setData(savedStudents);
            response.setMessage("Excel data uploaded and emails sent");
            response.setStatusCode(201);
            response.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Print full error in console
            e.printStackTrace();
            response.setStatusCode(500);
            response.setMessage("Failed to upload Excel file: " + e.getMessage());
            response.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
