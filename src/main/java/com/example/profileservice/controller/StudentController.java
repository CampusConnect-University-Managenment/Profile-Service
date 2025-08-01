package com.example.profileservice.controller;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.response.CommonResponse;
import com.example.profileservice.service.StudentService;
import com.example.profileservice.enumeration.ResponseStatus;
import com.example.profileservice.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/students")
public class StudentController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> AddStudents(@RequestBody StudentEntity addStudent) {
    StudentEntity savedStudent = studentService.AddStudents(addStudent);
    CommonResponse commonResponse = new CommonResponse();

    if (savedStudent != null) {
        // Step 1: Get personal email from JSON
        String personalEmail = addStudent.getStudentEmail();

        // Step 2: Generate official mail ID using roll number
        String rollNo = savedStudent.getStudentRollNo();
        String officialEmail = rollNo + "@university.edu";

        // Step 3: Use a static password (or you can randomize if needed)
        String staticPassword = "Student@123";

        // Step 4: Send the credentials to personal email
        emailService.sendStudentCredentials(personalEmail, officialEmail, staticPassword);

        commonResponse.setData(savedStudent);
        commonResponse.setMessage("New Student Added Successfully and Email Sent");
        commonResponse.setStatusCode(201);
        commonResponse.setStatus(ResponseStatus.SUCCESS);
        return ResponseEntity.status(201).body(commonResponse);
    } else {
        commonResponse.setStatusCode(400);
        commonResponse.setMessage("Failed to Add New Student");
        commonResponse.setData(null);
        commonResponse.setStatus(ResponseStatus.FAILED);
        return ResponseEntity.status(400).body(commonResponse);
    }
}

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> GetallStudents() {
        CommonResponse commonResponse = new CommonResponse();
        List<StudentEntity> StudentList = studentService.GetallStudents();

        if (StudentList != null && !StudentList.isEmpty()) {
            commonResponse.setData(StudentList);
            commonResponse.setMessage("Student list displayed Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("No Student Found");
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(404).body(commonResponse);
        }
    }

    @GetMapping("/rollno/{studentRollNo}")
    public ResponseEntity<CommonResponse> findByStudentRollNo(@PathVariable String studentRollNo) {
        CommonResponse commonResponse = new CommonResponse();
        Optional<StudentEntity> studentOpt = studentService.findByStudentRollNo(studentRollNo);

        if (studentOpt.isPresent()) {
            commonResponse.setData(studentOpt.get());
            commonResponse.setMessage("Student by RollNo displayed successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("No student found for RollNo: " + studentRollNo);
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
        }
    }


    @GetMapping("/department/{studentDepartment}")
    public ResponseEntity<CommonResponse> GetStudentByDepartment(@PathVariable String studentDepartment) {
        CommonResponse commonResponse = new CommonResponse();
        List<StudentEntity> getStudents = studentService.GetStudentByDepartment(studentDepartment);

        if (!getStudents.isEmpty()) {
            commonResponse.setData(getStudents);
            commonResponse.setMessage("Student list by department displayed Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("No student list found for department: " + studentDepartment);
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(404).body(commonResponse);
        }
    }

    @GetMapping("/totalcount")
    public ResponseEntity<CommonResponse> getTotalStudentCount() {
        CommonResponse response = new CommonResponse();
        long count = studentService.getTotalStudentCount();

        response.setStatusCode(200);
        response.setStatus(ResponseStatus.valueOf("SUCCESS"));
        response.setMessage("Total student count fetched successfully");
        response.setData(count);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/year/{studentYear}")
    public ResponseEntity<CommonResponse> GetStudentByYear(@PathVariable String studentYear) {
        CommonResponse commonResponse = new CommonResponse();
        List<StudentEntity> getStudents = studentService.GetStudentByYear(studentYear);

        if (!getStudents.isEmpty()) {
            commonResponse.setData(getStudents);
            commonResponse.setMessage("Student list by Year displayed Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("No student list found for Year: " + studentYear);
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(404).body(commonResponse);
        }
    }

    @PutMapping("/rollno/{studentRollNo}")
    public ResponseEntity<CommonResponse> UpdateStudent(@PathVariable String studentRollNo, @RequestBody StudentEntity updateStudent) {
        CommonResponse commonResponse = new CommonResponse();
        StudentEntity updated = studentService.UpdateStudent(studentRollNo, updateStudent);

        if (updated != null) {
            commonResponse.setData(updated);
            commonResponse.setMessage("Student Updated Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(400);
            commonResponse.setMessage("Student Update Failed");
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(400).body(commonResponse);
        }
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<CommonResponse> DeleteById(@PathVariable String studentId) {
        CommonResponse commonResponse = new CommonResponse();
        StudentEntity deleted = studentService.DeleteById(studentId);

        if (deleted != null) {
            commonResponse.setData(deleted);
            commonResponse.setMessage("Student Deleted Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("Student Not Found");
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.NOT_FOUND);
            return ResponseEntity.status(404).body(commonResponse);
        }
    }
}
