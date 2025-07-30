package com.example.profileservice.controller;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.response.CommonResponse;
import com.example.profileservice.service.StudentService;
import com.example.profileservice.enumeration.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> AddStudents(@RequestBody StudentEntity addStudent) {
        StudentEntity savedStudent = studentService.AddStudents(addStudent);
        CommonResponse commonResponse = new CommonResponse();

        if (savedStudent != null) {
            commonResponse.setData(savedStudent);
            commonResponse.setMessage("New Student Added Successfully");
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

    @GetMapping("/id/{studentId}")
    public ResponseEntity<CommonResponse> findById(@PathVariable String studentId){
        CommonResponse commonResponse = new CommonResponse();
        Optional<StudentEntity> getstudentbyId = studentService.findById(studentId);

        if (getstudentbyId.isPresent()) {
            commonResponse.setData(getstudentbyId.get());
            commonResponse.setMessage("Student displayed Successfully");
            commonResponse.setStatusCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.status(200).body(commonResponse);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("No student found with this Id: " + studentId);
            commonResponse.setData(null);
            commonResponse.setStatus(ResponseStatus.FAILED);
            return ResponseEntity.status(404).body(commonResponse);
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

    @PutMapping("/{studentId}")
    public ResponseEntity<CommonResponse> UpdateStudent(@PathVariable String studentId, @RequestBody StudentEntity updateStudent) {
        CommonResponse commonResponse = new CommonResponse();
        StudentEntity updated = studentService.UpdateStudent(studentId, updateStudent);

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
