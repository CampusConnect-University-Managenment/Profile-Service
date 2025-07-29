package com.example.profileservice.service;

import com.example.profileservice.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    List<StudentEntity> GetallStudents();
    StudentEntity AddStudents(StudentEntity studentEntity);
    List<StudentEntity> GetStudentByDepartment(String studentDepartment);
    StudentEntity DeleteById(String studentId);
    StudentEntity UpdateStudent(String studentId, StudentEntity updateStudent);
}
